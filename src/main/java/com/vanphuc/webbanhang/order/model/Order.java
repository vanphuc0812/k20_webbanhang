package com.vanphuc.webbanhang.order.model;


import com.vanphuc.webbanhang.common.model.BaseEntity;
import com.vanphuc.webbanhang.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = OrderEntity.TABLE_NAME)
public class Order extends BaseEntity {

    @ManyToOne
    @JoinTable(
            name = OrderEntity.OrderUser.TABLE_NAME,
            joinColumns = @JoinColumn(name = OrderEntity.OrderUser.ORDERID),
            inverseJoinColumns = @JoinColumn(name = OrderEntity.OrderUser.USERID)
    )
    private User user;
    private BigDecimal totalPrice;

    //    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(
//            name = OrderEntity.OrderProduct.TABLE_NAME,
//            joinColumns = @JoinColumn(name = OrderEntity.OrderProduct.ORDERID),
//            inverseJoinColumns = @JoinColumn(name = OrderEntity.OrderProduct.PRODUCTID)
//    )
    @OneToMany(mappedBy = "order",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<OrderProduct> orderProducts = new LinkedHashSet<>();
}
