package com.kakao.cafe.service;

import com.kakao.cafe.dto.LoginDTO;
import com.kakao.cafe.dto.UserDTO;

import java.util.List;

public interface UserService {
    long insertUser(UserDTO user);

    List<UserDTO> getUserList();

    UserDTO getUserById(long id);

    void updateUser(long id, UserDTO user, String password);

    UserDTO getUserByLoginData(LoginDTO login);
}
