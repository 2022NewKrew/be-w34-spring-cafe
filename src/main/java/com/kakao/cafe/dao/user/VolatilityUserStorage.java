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
    public void add(User user) {
        if (findUserByUserId(user.getUserId()) == null) {
            users.add(user);
            return;
        }
        throw new IllegalArgumentException("중복된 UserId를 갖는 사용자가 존재합니다.");
    }

    @Override
    public User get(String userId) {
        User user = findUserByUserId(userId);
        if (user == null) {
            throw new IllegalArgumentException("찾는 사용자가 존재하지 않습니다.");
        }
        return user;
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

        User user = findUserByUserId(userId);
        user.setName(name);
        user.setEmail(email);
    }

    private User findUserByUserId(String userId) {
        return users
                .stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }
}
