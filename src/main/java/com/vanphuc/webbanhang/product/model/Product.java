package com.vanphuc.webbanhang.product.model;

import com.vanphuc.webbanhang.common.model.BaseEntity;
import com.vanphuc.webbanhang.order.model.OrderProduct;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = ProductEntity.TABLE_NAME)
public class Product extends BaseEntity {
    @Column(name = ProductEntity.NAME)
    private String name;
    @Column(name = ProductEntity.CATALOG)
    private String catalog;
    @Column(name = ProductEntity.IMAGE)
    private String image;
    @Column(name = ProductEntity.PRICE)
    private BigDecimal price;

    //    @ManyToMany(mappedBy = OrderEntity.OrderProduct.ORDER_MAPPED_PRODUCT,
//            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    private Set<Order> orders = new LinkedHashSet<>();
    @OneToMany(mappedBy = "product",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<OrderProduct> orderProducts = new LinkedHashSet<>();
}
