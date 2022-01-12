package com.kakao.cafe.domain.user;

import java.util.List;
import java.util.Optional;

public interface FindUserPort {
    Optional<User> findByUserId(String userId);

    List<User> findAll();

    Optional<User> findByUserIdAndPassword(String userId, String password);
}
