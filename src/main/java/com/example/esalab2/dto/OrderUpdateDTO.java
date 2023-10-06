package com.example.esalab2.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderUpdateDTO {
    List<OrderItemDTO> content;

    String status;
}
