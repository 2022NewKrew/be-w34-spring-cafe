package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserRequestDTO;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(UserRequestDTO user);
    void update(UserRequestDTO user, Long id);
    Optional<User> findByUserId(String userId);
    List<User> findAll();
}
