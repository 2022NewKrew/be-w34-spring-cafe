package com.kakao.cafe.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUserId(String userId);
    List<User> findByUserIdAndPassword(String userId, String password);
}
