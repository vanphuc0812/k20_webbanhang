package com.vanphuc.webbanhang.user.model;

import com.vanphuc.webbanhang.common.model.BaseEntity;
import com.vanphuc.webbanhang.order.model.Order;
import com.vanphuc.webbanhang.order.model.OrderEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = UserEntity.User.TABLE_NAME)
public class User extends BaseEntity {

    @Column(name = UserEntity.User.USERNAME, unique = true, nullable = false)
    private String username;
    @Column(name = UserEntity.User.PASSWORD, nullable = false)
    private String password;
    @Column(name = UserEntity.User.EMAIL)
    private String email;

    @OneToMany(mappedBy = OrderEntity.OrderUser.ORDER_MAPPED_USER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Order> orders = new LinkedHashSet<>();

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}