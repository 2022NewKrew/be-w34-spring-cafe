package com.kakao.cafe.repository.user;

import com.kakao.cafe.model.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private final Users users;

    public InMemoryUserRepository() {
        this.users = new Users();
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public List<User> findAll() {
        return users.getUsers();
    }

    @Override
    public Optional<User> findById(UUID id) {
        return users.findById(id);
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return users.findByUserId(userId);
    }
}
