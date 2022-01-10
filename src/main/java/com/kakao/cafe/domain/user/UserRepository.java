package com.kakao.cafe.domain.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();
    Optional<User> findByUserId(String userId);
    Long save(User user);
}
