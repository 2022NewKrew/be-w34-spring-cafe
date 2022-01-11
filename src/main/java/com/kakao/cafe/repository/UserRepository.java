package com.kakao.cafe.repository;

import com.kakao.cafe.entity.User;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class UserRepository {

    private final List<User> data = new ArrayList<>();

    @Nullable
    public User create(User user) {
        // NOTE 중복 확인을 Repository에서 해야 할 지, Service에서 해야 할지?
        User existing = find(User::getUserId, user.getUserId()).orElse(null);
        if (existing != null) {
            // NOTE Exception을 던지는 것이 더 나을 것 같다. 옳은 접근인가?
            return null;
        }
        user.setId(data.size() + 1);
        data.add(user);
        return user;
    }

    public List<User> list() {
        return Collections.unmodifiableList(data);
    }

    @Nullable
    public User getById(long id) {
        return find(User::getId, id).orElse(null);
    }

    @Nullable
    public User getByUserId(String userId) {
        return find(User::getUserId, userId).orElse(null);
    }

    @Nullable
    public User login(String userId, String password) {
        Optional<User> found = find(User::getUserId, userId);
        if (found.isEmpty()) {
            return null;
        }
        User user = found.get();
        boolean ok = user.checkPassword(password);
        if (!ok) {
            return null;
        }
        return user;
    }

    private <T> Optional<User> find(Function<User, T> getter, T value) {
        return data.stream()
                .filter(user -> getter.apply(user).equals(value))
                .findFirst();
    }
}
