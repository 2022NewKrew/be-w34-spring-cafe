package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void insert(User user);
    Optional<User> selectById(Long id);
    Optional<User> selectByUserId(String userId);
    List<User> selectAll();
    void update(User user);
}
