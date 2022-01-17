package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.Users;

public interface UserRepository {

    void save(User user);

    Users findAll();

    User findById(String userId);
}
