package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;

import java.util.List;

public interface UserRepository {

    List<User> findAll();
    User findByUserId(String userId);
    int save(User user);

}
