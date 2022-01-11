package com.kakao.cafe.domain.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User add(User user);
    Optional<User> findById(long id);
    List<User> findAll();
}
