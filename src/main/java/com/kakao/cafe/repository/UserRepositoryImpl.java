package com.kakao.cafe.repository;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final List<User> users = new ArrayList<>();

    @Override
    public List<User> findAll() {
        return List.copyOf(users);
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return users.stream().filter(user -> user.getUserId().equals(userId)).findFirst();
    }

    public Long save(User user) {
        if(user.getId() == null) {
            return insertUser(user);
        }
        return updateUser(user);
    }

    private Long insertUser(User user) {
        users.add(user);
        setIdInUser(user);
        return user.getId();
    }

    private Long updateUser(User user) {
        users.set(user.getId().intValue(), user);
        return user.getId();
    }

    private void setIdInUser(User user) {
        int id = users.indexOf(user);
        user.setId((long) id);
    }
}
