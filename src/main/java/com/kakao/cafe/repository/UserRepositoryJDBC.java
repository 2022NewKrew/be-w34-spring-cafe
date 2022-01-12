package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("userRepositoryJDBC")
public class UserRepositoryJDBC implements UserRepository {
    @Override
    public void save(User newUser) {

    }

    @Override
    public Optional<List<User>> findAll() {
        return Optional.empty();
    }

    @Override
    public User findByUserId(String userId) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }
}
