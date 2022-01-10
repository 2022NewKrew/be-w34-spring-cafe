package com.kakao.cafe.repository;

import com.kakao.cafe.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {
    private static final Map<Long, UserEntity> db = new HashMap<>();
    private static Long seq = 1L;

    public UserEntity save(UserEntity entity) {
        entity.setId(seq++);
        db.put(entity.getId(), entity);
        return entity;
    }

    public Optional<UserEntity> findById(Long id) {
        return Optional.ofNullable(db.get(id));
    }
}
