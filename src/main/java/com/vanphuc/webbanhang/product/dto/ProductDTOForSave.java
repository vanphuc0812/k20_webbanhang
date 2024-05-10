package com.vanphuc.webbanhang.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTOForSave {
    private String name;
    private String catalog;
    private String image;
    private String price;
}

