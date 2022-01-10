package com.kakao.cafe.users;

import java.util.Optional;

public interface UserRepository {

    void update(User user);

    Optional<User> findById(long id);

    Optional<User> findByEmail(String email);

}
