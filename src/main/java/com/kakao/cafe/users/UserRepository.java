package com.kakao.cafe.users;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    default void update(User user) {}

    default Optional<User> validate(String id, String password) {return Optional.empty();}

    default Optional<User> findBySeq(long seq) {return Optional.empty();}

    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);

    List<User> findAll();

}
