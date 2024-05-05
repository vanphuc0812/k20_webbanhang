package com.vanphuc.webbanhang.user.controller;

import com.vanphuc.webbanhang.common.model.ResponeDTO;
import com.vanphuc.webbanhang.common.utils.ResponseUtil;
import com.vanphuc.webbanhang.user.dto.UserDTO;
import com.vanphuc.webbanhang.user.model.User;
import com.vanphuc.webbanhang.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                userService.findAll()
                , HttpStatus.OK
        );
    }

    @PostMapping("/save")
    public Object saveUser(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
        return ResponseUtil.get(
                userService.save(username, password, email), HttpStatus.OK
        );
    }

//    @GetMapping("login")
}
