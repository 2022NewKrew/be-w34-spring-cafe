package com.kakao.cafe.repository;

import com.kakao.cafe.model.User;
import com.kakao.cafe.model.UserSignUpDTO;
import com.kakao.cafe.model.UserViewDTO;

import java.util.List;

public interface UserRepository {
    void signUp(UserSignUpDTO userSignUpDTO);
    List<UserViewDTO> findAllUsers();
    UserViewDTO findUserByUserId(String userId);
}
