package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import java.util.Optional;
import java.util.UUID;

public interface SessionRepository {

    Optional<User> findBySessionId(UUID uuid);

    void save(UUID uuid, User user);

    boolean exist(UUID sessionId);

    void delete(UUID sessionId);
}
