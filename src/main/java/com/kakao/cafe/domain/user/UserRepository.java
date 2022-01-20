package com.kakao.cafe.domain.user;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {
    void add(User user);
    void update(User user);
    Optional<User> findById(long id);
    Optional<User> findByUserId(String userId);
    Optional<User> findByUserIdAndPassword(String userId, String password);
    List<User> findAll();
}
