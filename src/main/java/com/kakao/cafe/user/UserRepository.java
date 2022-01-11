package com.kakao.cafe.user;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.Users;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final Map<String, User> users = new ConcurrentHashMap<>();

    public void save(User user) {
        saveValidate(user);
        users.put(user.getUserId(), user);
    }

    private void saveValidate(User user) {
        if (users.containsKey(user.getUserId())) {
            throw new IllegalArgumentException("중복된 사용자입니다.");
        }
    }

    public Users findAll() {
        return new Users(new ArrayList<>(users.values()));
    }

    public User findById(String userId) {
        validateUser(userId);
        return users.get(userId);
    }

    private void validateUser(String userId) {
        if (!users.containsKey(userId)) {
            throw new IllegalArgumentException("존재하지 않는 아이디입니다.");
        }
    }
}
