package com.vanphuc.webbanhang.product.model;

import com.vanphuc.webbanhang.common.model.BaseEntity;
import com.vanphuc.webbanhang.order.model.Order;
import com.vanphuc.webbanhang.order.model.OrderEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Product extends BaseEntity {
    private String name;
    private String catalog;
    private String image;
    private String price;

    @ManyToMany(mappedBy = OrderEntity.OrderProduct.ORDER_MAPPED_PRODUCT,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Order> orders = new HashSet<>();
}
