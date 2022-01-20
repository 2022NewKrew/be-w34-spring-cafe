package com.kakao.cafe.user.domain;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Long persist(User user);

    Optional<User> find(Long id);

    Optional<User> find(String stringId);

    List<User> findAll();

    void updateUserInfo(User user);
}
