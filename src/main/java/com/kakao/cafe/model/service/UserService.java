package com.kakao.cafe.model.service;

import com.kakao.cafe.model.domain.User;
import com.kakao.cafe.model.dto.UserDTO;

import java.util.List;

public interface UserService {
    boolean registerUser(UserDTO userDTO);
    List<User> findAllUsers();
    User findUserById(Long id);
    User findUserByUserId(String userId);
    User findUserByLoginInfo(String userId, String password);
    boolean modifyUser(UserDTO userDTO);
    boolean withdrawUser(String userId, String password);
}
