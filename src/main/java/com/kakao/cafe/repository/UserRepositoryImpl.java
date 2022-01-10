package com.kakao.cafe.repository;

import com.kakao.cafe.DB.RogerDB;
import com.kakao.cafe.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final RogerDB rogerDB;

    @Autowired
    public UserRepositoryImpl(RogerDB rogerDB) {
        this.rogerDB = rogerDB;
    }

    @Override
    public void save(User newUser) {
        rogerDB.getUser().add(newUser);
    }

    @Override
    public Optional<List<User>> findAll() {
        return Optional.ofNullable(rogerDB.getUser());
    }

    @Override
    public User findByUserId(String userId) {
        return rogerDB.getUser()
                .stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst().orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        return rogerDB.getUser()
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst().orElse(null);
    }
}
