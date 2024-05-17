package com.vanphuc.webbanhang.security.service;

import com.vanphuc.webbanhang.common.exception.WBHBussinessException;
import com.vanphuc.webbanhang.security.model.UserDetailImpl;
import com.vanphuc.webbanhang.user.model.User;
import com.vanphuc.webbanhang.user.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new WBHBussinessException("User not found"));

        return UserDetailImpl.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(
                        Arrays.stream(user.getRoles()               // admin,user,guest
                                        .split(","))          // [admin,user,guest]
                                .map(role -> {
                                    return (GrantedAuthority) new SimpleGrantedAuthority(role.toUpperCase());
                                }).toList()
                )
                .build();
    }
}
