package com.vanphuc.webbanhang.user.service;

import com.vanphuc.webbanhang.common.utils.WBHMapper;
import com.vanphuc.webbanhang.user.dto.UserDTO;
import com.vanphuc.webbanhang.user.dto.UserDTOForSave;
import com.vanphuc.webbanhang.user.model.User;
import com.vanphuc.webbanhang.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service // = @Component + fucntion service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final WBHMapper mapper;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, WBHMapper mapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public User save(UserDTOForSave userDTOForSave) {
        User user = mapper.map(userDTOForSave, User.class);
        user.setPassword(passwordEncoder.encode(userDTOForSave.getPassword()));
        user.setRoles(userDTOForSave.getRoles().replace(" ", ""));
        return userRepository.save(user);
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map((user) -> {
            return mapper.map(user, UserDTO.class);
        }).toList();
    }

    @Override
    public JpaRepository<User, UUID> getRepository() {
        return userRepository;
    }

    @Override
    public ModelMapper getMapper() {
        return mapper;
    }
}
