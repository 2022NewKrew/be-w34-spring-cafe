package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.exception.CustomInvalidedSessionException;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class SessionMapRepository implements SessionRepository {

    private final Map<UUID, User> sessions = new ConcurrentHashMap<>();

    @Override
    public User findBySessionId(UUID uuid) {
        User user = sessions.get(uuid);
        validateUser(user);
        return user;
    }

    private void validateUser(User user) {
        if (Objects.isNull(user)) {
            throw new CustomInvalidedSessionException();
        }
    }

    @Override
    public void save(UUID uuid, User user) {
        sessions.put(uuid, user);
    }

    @Override
    public boolean exist(UUID sessionId) {
        return Objects.nonNull(sessionId) && sessions.containsKey(sessionId);
    }
}
