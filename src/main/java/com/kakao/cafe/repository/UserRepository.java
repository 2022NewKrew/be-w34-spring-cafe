package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.dto.UserResult;

import java.util.List;

public interface UserRepository {
    void save(User user);

    List<User> findAll();

    User findById(String userId);

    void update(User user);
}
