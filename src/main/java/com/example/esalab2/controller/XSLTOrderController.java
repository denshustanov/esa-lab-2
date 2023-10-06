package com.example.esalab2.entity.controller;

import com.example.esalab2.entity.dto.OrderDTO;
import com.example.esalab2.entity.dto.PageDTO;
import com.example.esalab2.entity.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.util.UUID;

@Controller
@RequestMapping("/api/xml/v1/orders")
public class XSLTOrderController {
    private final OrderService orderService;

    @Autowired
    public XSLTOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ModelAndView getOrders(@RequestParam(name = "size", required = false, defaultValue = "10") int size,
                                      @RequestParam(name = "page", required = false, defaultValue = "0") int page) throws JsonProcessingException {
        PageDTO<OrderDTO> orders = orderService.getAll(page, size);

        ModelAndView modelAndView = new ModelAndView("orders");
        Source source = new StreamSource(new ByteArrayInputStream(new XmlMapper().writeValueAsBytes(orders)));
        modelAndView.addObject(source);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getOrder(@PathVariable UUID id) throws JsonProcessingException {
        OrderDTO orderDTO = orderService.getOrder(id);

        ModelAndView modelAndView = new ModelAndView("order");
        Source source = new StreamSource(new ByteArrayInputStream(new XmlMapper().writeValueAsBytes(orderDTO)));
        modelAndView.addObject(source);

        return modelAndView;
    }
}
