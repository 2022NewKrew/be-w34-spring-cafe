package com.kakao.cafe.domain.repository;

import com.kakao.cafe.domain.entity.Session;
import com.kakao.cafe.domain.entity.SignUp;
import com.kakao.cafe.domain.entity.User;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.function.Function;

@Repository
public class UserRepository {

    private final List<User> data = new ArrayList<>();
    private final Map<String, Session> sessionMap = new HashMap<>();

    @Nullable
    public User create(SignUp signUp) {
        // NOTE 중복 확인을 Repository에서 해야 할 지, Service에서 해야 할지?
        User existing = getBy(User::getUserId, signUp.getUserId()).orElse(null);
        if (existing != null) {
            // NOTE Exception을 던지는 것이 더 나을 것 같다. 옳은 접근인가?
            return null;
        }
        long id = data.size() + 1;
        User user = signUp.createUser(id);
        data.add(user);
        return user;
    }

    public List<User> list() {
        return Collections.unmodifiableList(data);
    }

    @Nullable
    public User getById(long id) {
        return getBy(User::getId, id).orElse(null);
    }

    @Nullable
    public User getByUserId(String userId) {
        return getBy(User::getUserId, userId).orElse(null);
    }

    @Nullable
    public User login(String userId, String password) {
        Optional<User> found = getBy(User::getUserId, userId);
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

    public void saveSession(Session session) {
        sessionMap.put(session.getId(), session);
    }

    public Session getSession(String sessionId) {
        return sessionMap.get(sessionId);
    }

    private <T> Optional<User> getBy(Function<User, T> getter, T value) {
        return data.stream()
                .filter(user -> getter.apply(user).equals(value))
                .findFirst();
    }
}
