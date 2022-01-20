package com.kakao.cafe.dao.user;

import com.kakao.cafe.model.user.User;
import com.kakao.cafe.model.user.UserId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class VolatilityUserStorage implements UserDao {

    private final List<User> users;

    public VolatilityUserStorage() {
        this.users = new ArrayList<>();
    }

    @Override
    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    @Override
    public void addUser(User user) {
        users.add(
                new User(
                        user.getUserId(),
                        user.getPassword(),
                        user.getName(),
                        user.getEmail()));
    }

    @Override
    public Optional<User> findUserById(UserId userId) {
        return users
                .stream()
                .filter(user -> user.isUserId(userId))
                .findFirst();
    }

    @Override
    public int getSize() {
        return users.size();
    }

    @Override
    public void update(User newUser) {
        User user = findUserById(newUser.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("찾는 사용자가 없습니다."));
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
    }
}
