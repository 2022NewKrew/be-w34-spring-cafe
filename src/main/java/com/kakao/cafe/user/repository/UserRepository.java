package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {
    private static final Map<Long, UserEntity> db = new HashMap<>();
    private static Long seq = 1L;

    public UserEntity save(UserEntity entity) {
        if (entity.getId() == null) {
            return create(entity);
        }
        return update(entity);
    }

    public List<UserEntity> findAll() {
        return new ArrayList<>(db.values());
    }

    public Optional<UserEntity> findById(Long id) {
        return Optional.ofNullable(db.get(id));
    }

    public Optional<UserEntity> findByUserId(String userId) {
        return db.values().stream()
                .filter(user -> user.getUserId().equals(userId))
                .findAny();
    }

    private UserEntity create(UserEntity userEntity) {
        userEntity.setId(seq++);
        db.put(userEntity.getId(), userEntity);
        return userEntity;
    }

    private UserEntity update(UserEntity userEntity) {
        db.replace(userEntity.getId(), userEntity);
        return userEntity;
    }
}
