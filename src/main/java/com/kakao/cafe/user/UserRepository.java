package com.kakao.cafe.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Long add(User user);

    List<User> getAll();

    Optional<User> get(String username);

    Optional<User> get(Long id);

    void update(User user);

    void remove(Long id);
}
