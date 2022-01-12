package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Long insertUser(User user);
    List<User> findAll();
    Optional<User> findByUserId(String userId);
    Optional<User> findById(Long id);
    Long updateUser(User user);
}
