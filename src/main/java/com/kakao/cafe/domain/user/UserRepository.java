package com.kakao.cafe.domain.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User user);
    List<User> findAll();
    Optional<User> findById(long id);
    Optional<User> findByEmail(String email);
    void deleteAll();
}
