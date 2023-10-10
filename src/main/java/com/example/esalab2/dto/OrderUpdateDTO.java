package com.example.esalab2.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderUpdateDTO {
    List<OrderItemDTO> content;

    String status;
}
