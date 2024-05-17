package com.vanphuc.webbanhang.order.controller;

import com.vanphuc.webbanhang.common.utils.ResponseUtil;
import com.vanphuc.webbanhang.order.dto.OrderDTOForSave;
import com.vanphuc.webbanhang.order.dto.OrderProductDTOForSave;
import com.vanphuc.webbanhang.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderRestResource {
    private final OrderService orderService;

    public OrderRestResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/findAll")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Object findAll() {
        return orderService.findAll();
    }

    @GetMapping("/findByID")
    @PreAuthorize("hasAuthority('USER')")
    public Object findByID(UUID id) {
        return orderService.findById(id);
    }

    @GetMapping("/findOrderByUsername")
    public Object findOrderByUsername(@RequestParam String username) {
        return orderService.findOrderByUsername(username);
    }

    @GetMapping("/findOrderByProductID")
    public Object findOrderByProductID(@RequestParam UUID id) {
        return orderService.findOrderByProductID(id);
    }

    @PostMapping("/save")
    public Object save(@RequestBody OrderDTOForSave order) {
        return ResponseUtil.get(orderService.save(order), HttpStatus.OK);
    }

    @PostMapping("/{order-id}/addProducts")
    public Object addProducts(@PathVariable("order-id") UUID orderID, @RequestBody List<OrderProductDTOForSave> productList) {
        return ResponseUtil.get(orderService.addProducts(orderID, productList), HttpStatus.OK);
    }

    @PostMapping("/{order-id}/removeProducts")
    public Object removeProducts(@PathVariable("order-id") UUID orderID, @RequestBody List<OrderProductDTOForSave> productList) {
        return ResponseUtil.get(orderService.removeProducts(orderID, productList), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public Object delete(@RequestParam UUID orderID) {
        orderService.deleteByID(orderID);
        return ResponseUtil.get("Delete Order Success", HttpStatus.OK);
    }
}
