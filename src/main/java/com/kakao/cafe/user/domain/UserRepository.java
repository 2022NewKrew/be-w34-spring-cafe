package com.kakao.cafe.user.domain;

import com.kakao.cafe.user.repository.UserCreateRequestDTO;
import com.kakao.cafe.user.repository.UserUpdateRequestDTO;

import java.util.ArrayList;
import java.util.Optional;

public interface UserRepository {

    Long persist(UserCreateRequestDTO dto);

    Optional<User> find(Long id);

    Optional<User> find(String stringId);

    ArrayList<User> findAll();

    void updateUserInfo(UserUpdateRequestDTO dto);
}
