package com.kakao.cafe.web.repository.user;

import com.kakao.cafe.web.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(int id);
    Optional<User> findByUserId(String userId);
    List<User> findAll();


}
