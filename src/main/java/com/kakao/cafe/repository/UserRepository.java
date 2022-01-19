package com.kakao.cafe.repository;

import com.kakao.cafe.domain.user.Password;
import com.kakao.cafe.domain.user.User;

import com.kakao.cafe.domain.user.UserName;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    void save(User user);

    List<User> findAll();

    Optional<User> findUserByName(UserName userName);

    Optional<User> findUserById(UUID id);

    void update(User user);

    Optional<User> findUserByUserNameAndPassword(UserName userName, Password password);
}
