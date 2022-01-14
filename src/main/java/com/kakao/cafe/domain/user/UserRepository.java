package com.kakao.cafe.domain.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User user);

    User edit(String userId, User user);

    Optional<User> findById(String userId);

    List<User> findAll();
}
