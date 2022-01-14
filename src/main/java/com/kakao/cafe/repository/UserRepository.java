package com.kakao.cafe.repository;

import com.kakao.cafe.domain.dto.UserSignUpDTO;
import com.kakao.cafe.domain.model.User;

import java.util.List;

public interface UserRepository {
    void signUp(UserSignUpDTO userSignUpDTO);
    List<User> findAllUsers();
    User findUserByUserId(String userId);
}
