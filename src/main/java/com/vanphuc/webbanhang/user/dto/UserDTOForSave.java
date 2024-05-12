package com.vanphuc.webbanhang.user.dto;

import com.vanphuc.webbanhang.validation.validateAnotation.UniqueUsername;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOForSave {
    @Size(min = 5, max = 50)
    @NotNull(message = "123")
    @NotBlank
    @UniqueUsername()
    private String username;
    private String password;
    private String email;
}
