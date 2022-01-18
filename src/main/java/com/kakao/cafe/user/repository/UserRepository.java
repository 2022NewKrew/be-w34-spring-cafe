package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    List<User> findAll();
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
}
