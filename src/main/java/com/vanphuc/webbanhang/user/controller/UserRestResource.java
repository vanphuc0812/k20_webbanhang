package com.vanphuc.webbanhang.user.controller;

import com.vanphuc.webbanhang.common.model.ResponeDTO;
import com.vanphuc.webbanhang.common.utils.ResponseUtil;
import com.vanphuc.webbanhang.user.dto.UserDTO;
import com.vanphuc.webbanhang.user.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserRestResource {

    @GetMapping("/findall")
    public Object findAllUser() {
        return ResponseUtil.get(
                new UserDTO("admin", "1234"), HttpStatus.NOT_FOUND
        );
    }

}
