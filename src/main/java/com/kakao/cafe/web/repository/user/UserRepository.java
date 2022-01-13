package com.kakao.cafe.web.repository.user;

import com.kakao.cafe.web.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByUserId(String userId);
    List<User> findAll();


}
