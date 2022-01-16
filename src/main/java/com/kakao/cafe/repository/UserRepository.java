package com.kakao.cafe.repository;

import com.kakao.cafe.domain.dto.UserSignUpDTO;
import com.kakao.cafe.domain.model.User;

import java.util.List;

public interface UserRepository {
    void signUp(User user);
    List<User> findAllUsers();
    User findUserByUserId(String userId);
}
