package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import java.util.UUID;

public interface SessionRepository {

    User findBySessionId(UUID uuid);

    void save(UUID uuid, User user);
}
