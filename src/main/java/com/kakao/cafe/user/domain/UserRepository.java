package com.kakao.cafe.user.domain;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findByUsername(String username);

    List<User> findAll();
}
