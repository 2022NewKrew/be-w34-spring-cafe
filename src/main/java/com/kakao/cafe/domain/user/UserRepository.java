package com.kakao.cafe.domain.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User user);

    List<User> findAll();

    Optional<User> findByUserId(String userId);

    void update(User user);
}
