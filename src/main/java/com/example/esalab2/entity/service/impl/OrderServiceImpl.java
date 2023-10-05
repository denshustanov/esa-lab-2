package com.example.esalab2.entity.service.impl;

import com.example.esalab2.entity.Order;
import com.example.esalab2.entity.OrderStatus;
import com.example.esalab2.entity.dto.*;
import com.example.esalab2.entity.service.OrderNumberGenerator;
import com.example.esalab2.entity.service.OrderService;
import com.example.esalab2.exception.ResourceNotFoundException;
import com.example.esalab2.respository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper mapper;

    private final OrderNumberGenerator orderNumberGenerator;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper mapper, OrderNumberGenerator orderNumberGenerator) {
        this.orderRepository = orderRepository;
        this.mapper = mapper;
        this.orderNumberGenerator = orderNumberGenerator;
    }


    @Override
    public OrderDTO getOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> notFoundSupplier(orderId));

        return mapper.map(order, OrderDTO.class);
    }

    @Override
    public PageDTO<OrderDTO> getAll(int page, int size) {
        return PageMapper.map(orderRepository.findAll(PageRequest.of(page, size)), e -> mapper.map(e, OrderDTO.class));
    }

    @Override
    public OrderDTO save(OrderCreationDTO dto) {
        Order order = mapper.map(dto, Order.class);

        order.setNumber(orderNumberGenerator.generate());
        order.setStatus(OrderStatus.DRAFT);
        order.getContent().forEach(orderItem -> orderItem.setOrder1(order));
        Order newOrder = orderRepository.save(order);
        return mapper.map(newOrder, OrderDTO.class);
    }

    @Override
    public OrderDTO update(UUID id, OrderUpdateDTO dto) {
        Order order = orderRepository.findById(id).orElseThrow(() -> notFoundSupplier(id));
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        mapper.map(dto, order);
        order.getContent().forEach(orderItem -> orderItem.setOrder1(order));

        Order newOrder = orderRepository.save(order);
        return mapper.map(newOrder, OrderDTO.class);
    }

    @Override
    public void delete(UUID id) {
        if(orderRepository.existsById(id)){
            orderRepository.deleteById(id);
            return;
        }

        throw  notFoundSupplier(id);
    }

    private ResourceNotFoundException notFoundSupplier(UUID id){
        return new ResourceNotFoundException(String.format("Order with id %s not found", id));
    }
}
