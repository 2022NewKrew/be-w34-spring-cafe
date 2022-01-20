package com.kakao.cafe.service;

import com.kakao.cafe.dto.LoginDTO;
import com.kakao.cafe.dto.UserDTO;
import org.springframework.ui.Model;

import java.util.List;

public interface UserService {
    void insertUser(UserDTO user);

    List<UserDTO> getUserList();

    void getUserById(long id, Model model);

    void updateUser(long id, UserDTO user, String password);

    void loginByLoginData(LoginDTO login);

    void logOut();
}
