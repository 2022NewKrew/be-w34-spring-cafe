package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemoryUserRepository implements UserRepository {

    private final List<User> userDataBase = new ArrayList<>();


    @Override
    public void save(User user) {
        userDataBase.add(user);
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return userDataBase.stream()
                .filter(user -> user.getUserId().equals(userId))
                .findAny();
    }

    @Override
    public List<User> findAll() {
        return List.copyOf(userDataBase);
    }

    @Override
    public void update(User user) {
        throw new UnsupportedOperationException();
    }

}

