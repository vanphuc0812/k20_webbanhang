package com.vanphuc.webbanhang.user.controller;

import com.vanphuc.webbanhang.common.utils.ResponseUtil;
import com.vanphuc.webbanhang.user.dto.UserDTO;
import com.vanphuc.webbanhang.user.dto.UserDTOForSave;
import com.vanphuc.webbanhang.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserRestResource {
    private UserService userService;

    public UserRestResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findall")
    public Object findAllUser() {
        return ResponseUtil.get(
                userService.findAll(UserDTO.class)
                , HttpStatus.OK
        );
    }

    @PostMapping("/save")
    public Object saveUser(@RequestBody UserDTOForSave userDTOForSave) {
        return ResponseUtil.get(
                userService.save(userDTOForSave), HttpStatus.OK
        );
    }
}
