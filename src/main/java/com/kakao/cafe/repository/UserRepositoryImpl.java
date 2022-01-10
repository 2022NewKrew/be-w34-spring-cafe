package com.kakao.cafe.repository;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final List<User> users;

    public UserRepositoryImpl() {
        this.users = new ArrayList<>();
    }

    @Override
    public List<User> findAll() {
        return List.copyOf(users);
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = null;
        try {
            user = users.get(id.intValue());
        } catch (IndexOutOfBoundsException ignored) {}

        return Optional.ofNullable(user);
    }

    public Long save(User user) {
        users.add(user);
        setIdInUser(user);
        return user.getId();
    }

    private void setIdInUser(User user) {
        int id = users.indexOf(user);
        user.setId((long) id);
    }
}
