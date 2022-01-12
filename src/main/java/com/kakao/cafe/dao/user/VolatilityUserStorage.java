package com.kakao.cafe.dao.user;

import com.kakao.cafe.model.User;
import com.kakao.cafe.utility.NullChecker;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@Primary
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
    public void addUser(String userId, String password, String name, String email) {
        users.stream()
                .filter(tempUser -> tempUser.isUserId(userId))
                .findAny()
                .ifPresentOrElse(
                        User -> {
                            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
                        },
                        () -> users.add(new User(userId, password, name, email))
                );
    }

    @Override
    public User findUserById(String userId) {
        return users
                .stream()
                .filter(user -> user.isUserId(userId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("찾은 사용자가 없습니다."));
    }

    @Override
    public int getSize() {
        return users.size();
    }

    @Override
    public void update(String userId, String name, String email) {
        NullChecker.checkNotNull(userId);
        NullChecker.checkNotNull(name);
        NullChecker.checkNotNull(email);

        User user = findUserById(userId);
        user.setName(name);
        user.setEmail(email);
    }
}
