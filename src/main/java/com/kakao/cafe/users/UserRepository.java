package com.kakao.cafe.users;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    default void update(User user) {}

    default boolean validate(String id, String password) {return false;}

    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);

    List<User> findAll();

}
