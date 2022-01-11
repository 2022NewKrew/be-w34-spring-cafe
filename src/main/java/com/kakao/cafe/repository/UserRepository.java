package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepository {
    User save(User user);

    List<User> findAll();

    User findByUserId(String userId);
}
