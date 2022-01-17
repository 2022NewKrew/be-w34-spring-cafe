package com.kakao.cafe.repository;

import com.kakao.cafe.dto.LoginDTO;
import com.kakao.cafe.dto.UserDTO;

import java.util.List;

public interface UserRepository {
    long insertUser(UserDTO user);

    UserDTO getUserById(long id);

    List<UserDTO> getAllUser();

    int updateUser(long id, UserDTO user);

    UserDTO getUserByLoginData(LoginDTO login);
}
