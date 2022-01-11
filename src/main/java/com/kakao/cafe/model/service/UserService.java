package com.kakao.cafe.model.service;

import com.kakao.cafe.model.domain.User;
import com.kakao.cafe.model.dto.UserDTO;

import java.util.List;

public interface UserService {
    boolean registerUser(UserDTO userDTO);
    List<UserDTO> findAllUsers();
    UserDTO findUserById(Long id);
    UserDTO findUserByUserId(String userId);
    UserDTO findUserByLoginInfo(String userId, String password);
    boolean modifyUser(UserDTO userDTO);
    boolean withdrawUser(String userId, String password);
}
