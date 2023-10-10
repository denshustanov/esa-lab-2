package com.example.esalab2.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageDTO<T> {
    long number;

    long size;

    long totalCount;

    long totalPages;

    List<T> items;
}
