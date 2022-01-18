package com.kakao.cafe.domain.user;

import java.util.List;
import java.util.Optional;

public interface UserDaoPort {
    List<User> findAll();
    Optional<User> findByUserId(String userId);
    Optional<User> findByUserIdAndPassword(String userId, String password);
    void save(User user);
    void update(User user);
}
