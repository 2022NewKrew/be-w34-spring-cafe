package com.kakao.cafe.dao.user;

import com.kakao.cafe.model.User;
import com.kakao.cafe.utility.NullChecker;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public void addUser(String userId, String password, String name, String email) {
        checkExist(userId);

        users.add(new User(userId, password, name, email));
    }

    @Override
    public Optional<User> findUserById(String userId) {
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
    public void update(String userId, String name, String email) {
        NullChecker.checkNotNull(userId);
        NullChecker.checkNotNull(name);
        NullChecker.checkNotNull(email);

        User user = findUserById(userId).orElseThrow(() -> new IllegalArgumentException("찾는 사용자가 없습니다."));
        user.setName(name);
        user.setEmail(email);
    }

    private void checkExist(String userId) {
        if (containUser(userId)) {
            throw new IllegalArgumentException("이미 사용중인 아이디 입니다.");
        }
    }

    private boolean containUser(String userId) {
        return users
                .stream()
                .anyMatch(user -> user.isUserId(userId));
    }
}
