package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class SessionMapRepository implements SessionRepository {

    private final Map<UUID, User> sessions = new ConcurrentHashMap<>();

    @Override
    public Optional<User> findBySessionId(UUID uuid) {
        return Optional.of(sessions.get(uuid));
    }

    @Override
    public void save(UUID uuid, User user) {
        sessions.put(uuid, user);
    }

    @Override
    public boolean exist(UUID sessionId) {
        return Objects.nonNull(sessionId) && sessions.containsKey(sessionId);
    }

    @Override
    public void delete(UUID sessionId) {
        sessions.remove(sessionId);
    }
}
