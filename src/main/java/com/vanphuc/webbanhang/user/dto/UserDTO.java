package com.vanphuc.webbanhang.user.dto;

import com.vanphuc.webbanhang.user.model.UserEntity;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String email;
}
