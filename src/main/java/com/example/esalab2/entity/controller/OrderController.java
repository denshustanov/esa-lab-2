package com.example.esalab2.entity.controller;

import com.example.esalab2.entity.dto.OrderCreationDTO;
import com.example.esalab2.entity.dto.OrderDTO;
import com.example.esalab2.entity.dto.OrderUpdateDTO;
import com.example.esalab2.entity.dto.PageDTO;
import com.example.esalab2.entity.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<PageDTO<OrderDTO>> getAll(
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "page", required = false, defaultValue = "10") int page){
        return ResponseEntity.ok(orderService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> get(@PathVariable UUID id){
        return ResponseEntity.ok(orderService.getOrder(id));
    }

    @PostMapping
    public ResponseEntity<OrderDTO> postOrder(@RequestBody OrderCreationDTO dto){
        return ResponseEntity.ok(orderService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> putOrder(
            @PathVariable UUID id,
            @RequestBody OrderUpdateDTO dto){
        return ResponseEntity.ok(orderService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable UUID id){
        orderService.delete(id);
        return ResponseEntity.ok().build();
    }
}
