package com.vanphuc.webbanhang.product.dto;

import com.vanphuc.webbanhang.order.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private UUID id;
    private String name;
    private String catalog;
    private String image;
    private String price;
    private Set<Order> orders;
}
