package com.vanphuc.webbanhang.user.service;

import com.vanphuc.webbanhang.common.utils.WBHMapper;
import com.vanphuc.webbanhang.user.dto.UserDTO;
import com.vanphuc.webbanhang.user.model.User;
import com.vanphuc.webbanhang.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // = @Component + fucntion service
public class UserService {
    private UserRepository userRepository;
    private WBHMapper mapper;

    public UserService(UserRepository userRepository, WBHMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public User save(String username, String password, String email) {
        User user = new User(username, password, email);
        return userRepository.save(user);
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map((user) -> {
            return mapper.map(user, UserDTO.class);
        }).toList();
    }
}
