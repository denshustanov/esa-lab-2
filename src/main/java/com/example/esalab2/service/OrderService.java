package com.example.esalab2.entity.service;


import com.example.esalab2.entity.dto.OrderCreationDTO;
import com.example.esalab2.entity.dto.OrderDTO;
import com.example.esalab2.entity.dto.OrderUpdateDTO;
import com.example.esalab2.entity.dto.PageDTO;

import java.util.UUID;

public interface OrderService {
    OrderDTO getOrder(UUID orderId);

    PageDTO<OrderDTO> getAll(int page, int size);

    OrderDTO save(OrderCreationDTO dto);

    OrderDTO update(UUID id, OrderUpdateDTO dto);

    void delete(UUID id);
}
