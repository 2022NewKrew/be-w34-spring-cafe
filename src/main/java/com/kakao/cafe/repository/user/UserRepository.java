package com.kakao.cafe.repository.users;

import com.kakao.cafe.domain.User;

import java.util.List;

public interface UserRepository {
    Long save(User user);

    List<User> findAll();

    User findByUserId(String userId);
}
