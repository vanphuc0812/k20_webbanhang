package com.vanphuc.webbanhang.order.dto;


import com.vanphuc.webbanhang.product.dto.ProductDTO;
import com.vanphuc.webbanhang.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private UUID id;
    private UserDTO user;
    private Set<ProductDTO> products;
    private BigDecimal totalPrice;
}
