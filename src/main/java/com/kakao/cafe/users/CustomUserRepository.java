package com.kakao.cafe.users;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Primary
@Repository
public class CustomUserRepository implements UserRepository {

    public CustomUserRepository() {
    }

    @Override
    public void update(User user) {
        System.out.println("TODO");
    }

    @Override
    public Optional<User> findById(long id) {
        System.out.println("TODO");
        return Optional.of(null);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        System.out.println("TODO");
        return Optional.of(null);
    }

}
