package com.example.esalab2.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderItemDTO {
    long quantity;
    UUID productId;
}
