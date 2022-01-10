package com.kakao.cafe.Repository;

import com.kakao.cafe.domain.User;

import java.util.List;


public interface UserRepository {
    void save(User user);

    List<User> getAllUser();

    User findById(String userId);
}
