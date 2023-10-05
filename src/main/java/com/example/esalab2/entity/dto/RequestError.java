package com.example.esalab2.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestError {
    public static final String NOT_FOUND = "notFound";

    String code;
    String message;

}
