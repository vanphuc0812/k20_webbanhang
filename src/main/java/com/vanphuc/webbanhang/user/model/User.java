package com.vanphuc.webbanhang.user.model;

import com.vanphuc.webbanhang.common.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = UserEntity.User.TABLE_NAME)
public class User extends BaseEntity {

    @Column(name = UserEntity.User.USERNAME)
    private String username;
    @Column(name = UserEntity.User.PASSWORD)
    private String password;
    @Column(name = UserEntity.User.EMAIL)
    private String email;
}