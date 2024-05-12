package com.vanphuc.webbanhang.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTOForUpdate {
    private UUID id;
    private String name;
    private String catalog;
    private String image;
    private BigDecimal price;
}

