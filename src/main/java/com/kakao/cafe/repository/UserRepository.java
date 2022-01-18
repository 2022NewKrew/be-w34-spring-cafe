package com.kakao.cafe.repository;

import com.kakao.cafe.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    User save(User user);
    List<User> findAll();
    User findById(String userId);
    User findByMail(String email);
}
