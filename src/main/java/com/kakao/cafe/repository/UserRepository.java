package com.kakao.cafe.repository;

import com.kakao.cafe.vo.User;

import java.util.List;

public interface UserRepository {
    long insertUser(User user);

    User getUserById(long id);

    List<User> getAllUser();

    int updateUser(User user);
}
