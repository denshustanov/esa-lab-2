package com.example.esalab2.entity.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class OrderCreationDTO {
    UUID customerId;

    List<OrderItemDTO> content;
}
