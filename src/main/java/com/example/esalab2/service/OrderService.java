package com.example.esalab2.service;


import com.example.esalab2.dto.OrderCreationDTO;
import com.example.esalab2.dto.OrderDTO;
import com.example.esalab2.dto.OrderUpdateDTO;
import com.example.esalab2.dto.PageDTO;

import java.util.UUID;

public interface OrderService {
    OrderDTO getOrder(UUID orderId);

    PageDTO<OrderDTO> getAll(int page, int size);

    OrderDTO save(OrderCreationDTO dto);

    OrderDTO update(UUID id, OrderUpdateDTO dto);

    void delete(UUID id);
}
