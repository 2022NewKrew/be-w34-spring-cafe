package com.kakao.cafe.service;

import com.kakao.cafe.dto.LoginDTO;
import com.kakao.cafe.dto.UserDTO;

import java.util.List;

public interface UserService {
    long insertUser(UserDTO user);

    List<UserDTO> getUserList();

    UserDTO getUserById(long id);

    int updateUser(long id, UserDTO user);

    UserDTO getUserByLoginData(LoginDTO login);
}
