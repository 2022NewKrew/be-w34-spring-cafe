package com.kakao.cafe.user;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    void create(User user);

    List<User> readAll();

    Optional<User> read(Long id);

    Optional<User> read(String username);

    void update(User user);

    void delete(Long id);
}
