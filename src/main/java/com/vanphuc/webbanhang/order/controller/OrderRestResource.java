package com.vanphuc.webbanhang.order.controller;

import com.vanphuc.webbanhang.common.utils.ResponseUtil;
import com.vanphuc.webbanhang.order.dto.OrderDTO;
import com.vanphuc.webbanhang.order.dto.OrderDTOForSave;
import com.vanphuc.webbanhang.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderRestResource {
    private final OrderService orderService;

    public OrderRestResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/findAll")
    public Object findAll() {
        return orderService.findAll(OrderDTO.class);
    }

    @GetMapping("/findByID")
    public Object findByID(UUID id) {
        return orderService.findById(id);
    }

    @PostMapping("/save")
    public Object save(OrderDTOForSave order) {
        return ResponseUtil.get(orderService.save(order), HttpStatus.OK);
    }
}
