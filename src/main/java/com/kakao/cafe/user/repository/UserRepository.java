package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;

import java.util.ArrayList;

public interface UserRepository {

    User find(Long id);

    ArrayList<User> findAll();

    Long persist(UserCreateRequestDTO dto);

    Long findDBIdById(String stringId);

    String findStringIdByDBId(Long id);

    String findPasswordByDBId(Long userId);

    void updateUserInfo(UserUpdateRequestDTO dto);
}
