package com.kakao.cafe.user.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository {

    int save(User user);

    List<User> findAll();

    User findByIdOrNull(String userId);

    void delete(User user);

    void update(User user);
}
