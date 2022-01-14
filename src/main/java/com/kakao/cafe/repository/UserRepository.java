package com.kakao.cafe.repository;

import com.kakao.cafe.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {
    User save(User user);
    Optional<List<User>> findAll();
    User findById(String userId);
}
