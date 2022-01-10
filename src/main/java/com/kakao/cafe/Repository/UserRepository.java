package com.kakao.cafe.Repository;

import com.kakao.cafe.domain.User;

import java.util.List;


public interface UserRepository {
    void create(User user);

    List<User> getUserList();

    User findById(String userId);
}
