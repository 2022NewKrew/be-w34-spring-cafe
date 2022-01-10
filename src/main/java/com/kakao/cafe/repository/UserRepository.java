package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;

import java.util.List;

public interface UserRepository {
    User save(User member);

    List<User> findAll();
}
