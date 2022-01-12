package com.kakao.cafe.domain.user;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {
    void add(User user);
    Optional<User> findById(long id);
    List<User> findAll();
}
