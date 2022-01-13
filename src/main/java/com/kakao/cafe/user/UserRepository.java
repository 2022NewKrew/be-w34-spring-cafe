package com.kakao.cafe.user;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.Users;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final Map<String, User> users = new ConcurrentHashMap<>();

    public void save(User user) {
        User beforeUser = users.putIfAbsent(user.getUserId(), user);
        saveValidate(beforeUser);
    }

    private void saveValidate(User user) {
        if (Objects.nonNull(user)) {
            throw new IllegalArgumentException("중복된 사용자입니다.");
        }
    }

    public Users findAll() {
        return new Users(new ArrayList<>(users.values()));
    }

    public User findById(String userId) {
        User user = users.get(userId);
        validateUser(user);
        return user;
    }

    private void validateUser(User user) {
        if (Objects.isNull(user)) {
            throw new IllegalArgumentException("존재하지 않는 아이디입니다.");
        }
    }
}
