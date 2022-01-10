package com.kakao.cafe.user.domain.repository;

import com.kakao.cafe.user.domain.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserRepository {
    Optional<User> getUser(String userId);
    List<User> getAllUsers();
    void save(User user);
}
