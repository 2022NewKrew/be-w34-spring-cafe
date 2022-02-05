package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Password;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.UserId;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User user);

    Optional<User> findByUserId(UserId UserId);

    List<User> findAll();

    void remove(User user);

    void update(User user);

    Optional<User> findByUserIdAndPassword(UserId userId, Password password);
}
