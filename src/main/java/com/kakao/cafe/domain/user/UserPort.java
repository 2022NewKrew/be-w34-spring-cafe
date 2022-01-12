package com.kakao.cafe.domain.user;

import java.util.List;
import java.util.Optional;

public interface UserPort {
    Optional<User> findByUserId(String userId);

    void save(User user);

    List<User> findAll();
}
