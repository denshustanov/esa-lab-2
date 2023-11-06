package com.example.esalab2.controller;

import com.example.esalab2.dto.OrderCreationDTO;
import com.example.esalab2.dto.OrderDTO;
import com.example.esalab2.dto.OrderUpdateDTO;
import com.example.esalab2.dto.PageDTO;
import com.example.esalab2.service.OrderService;
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

    @GetMapping(produces = {"application/json", "application/xml"})
    public ResponseEntity<PageDTO<OrderDTO>> getAll(
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "page", required = false, defaultValue = "10") int page) {
        return ResponseEntity.ok(orderService.getAll(page, size));
    }

    @GetMapping(path = "/{id}",
            produces = {"application/json", "application/xml"})
    public ResponseEntity<OrderDTO> get(@PathVariable UUID id) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }

    @PostMapping(produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public ResponseEntity<OrderDTO> postOrder(@RequestBody OrderCreationDTO dto) {
        return ResponseEntity.ok(orderService.save(dto));
    }

    @PutMapping(path = "/{id}",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public ResponseEntity<OrderDTO> putOrder(
            @PathVariable UUID id,
            @RequestBody OrderUpdateDTO dto) {
        return ResponseEntity.ok(orderService.update(id, dto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable UUID id) {
        orderService.delete(id);
        return ResponseEntity.ok().build();
    }
}
