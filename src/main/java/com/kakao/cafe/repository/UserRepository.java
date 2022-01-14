package com.kakao.cafe.repository;

import com.kakao.cafe.domain.dto.UserSignUpDTO;
import com.kakao.cafe.domain.dto.UserViewDTO;

import java.util.List;

public interface UserRepository {
    void signUp(UserSignUpDTO userSignUpDTO);
    List<UserViewDTO> findAllUsers();
    UserViewDTO findUserByUserId(String userId);
}
