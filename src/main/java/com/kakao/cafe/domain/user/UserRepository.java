package com.kakao.cafe.domain.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void insert(User user);

    User update(String userId, User user);

    Optional<User> findById(String userId);

    List<User> findAll();
}
