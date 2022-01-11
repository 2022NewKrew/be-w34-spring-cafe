package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    public void save(User user);
    public Optional<User> findByUserId(String UserId);
    public List<User> findAll();
}
