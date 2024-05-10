package com.vanphuc.webbanhang.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTOForUpdate {
    private UUID id;
    private String name;
    private String catalog;
    private String image;
    private BigDecimal price;
}

