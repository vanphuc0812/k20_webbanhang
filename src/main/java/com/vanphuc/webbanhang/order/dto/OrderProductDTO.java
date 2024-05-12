package com.vanphuc.webbanhang.order.dto;

import com.vanphuc.webbanhang.product.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductDTO {
    private ProductDTO product;
    private int quantity;
}
