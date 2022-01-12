package com.kakao.cafe.repository;

import com.kakao.cafe.domain.user.User;

import com.kakao.cafe.domain.user.UserId;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    List<User> findAll();

    Optional<User> findUserById(UserId id);
}
