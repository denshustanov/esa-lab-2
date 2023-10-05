package com.example.esalab2.entity.dto;

import org.springframework.data.domain.Page;

import java.util.function.Function;

public class PageMapper {
    public static <E, R> PageDTO<R> map(Page<E> source, Function<E, R> mapper){
        PageDTO<R> dto = new PageDTO<>();

        dto.setItems(source.get().map(mapper).toList());
        dto.setSize(source.getSize());
        dto.setNumber(source.getNumber());
        dto.setTotalPages(source.getTotalPages());
        dto.setTotalCount(source.getTotalElements());

        return dto;
    }
}
