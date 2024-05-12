package com.vanphuc.webbanhang.order.model;

import com.vanphuc.webbanhang.product.model.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct {
    @GeneratedValue
    @Id
    private UUID uuid;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;
}
