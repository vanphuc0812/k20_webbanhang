package com.vanphuc.webbanhang.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTOForSave {
    private String name;
    private String catalog;
    private String image;
    private String price;
}

