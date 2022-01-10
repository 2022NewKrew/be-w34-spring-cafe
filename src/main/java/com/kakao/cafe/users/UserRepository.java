package com.kakao.cafe.users;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void update(User user);

    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);

    List<User> getAllUsers();

}
