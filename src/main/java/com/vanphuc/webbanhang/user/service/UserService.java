package com.vanphuc.webbanhang.user.service;

import com.vanphuc.webbanhang.common.service.GenericService;
import com.vanphuc.webbanhang.user.dto.UserDTO;
import com.vanphuc.webbanhang.user.dto.UserDTOForSave;
import com.vanphuc.webbanhang.user.model.User;

import java.util.UUID;

public interface UserService extends GenericService<User, UserDTO, UUID> {
    User save(UserDTOForSave userDTOForSave);
}
