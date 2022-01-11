package com.kakao.cafe.repository.user;

import com.kakao.cafe.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserMemoryRepository implements UserRepository {
    private final List<UserEntity> userMemoryDatabase = new ArrayList<>();

    @Override
    public <S extends UserEntity> S save(S entity) {
        entity.putUserId(getAutoGeneratedKey());
        entity.putCreatedDate();
        entity.putUpdatedDate();

        userMemoryDatabase.add(entity);
        return entity;
    }

    private Long getAutoGeneratedKey() {
        return Long.valueOf(userMemoryDatabase.size());
    }

    @Override
    public UserEntity findOne(String primaryKey) {
        return userMemoryDatabase.stream()
                .filter(u -> u.getId().equals(primaryKey))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public List<UserEntity> findAll() {
        return userMemoryDatabase;
    }

    @Override
    public Long count() {
        return Long.valueOf(userMemoryDatabase.size());
    }

    @Override
    public void delete(UserEntity entity) {
        userMemoryDatabase.remove(entity);
    }

    @Override
    public boolean exists(String primaryKey) {
        return userMemoryDatabase.stream()
                .filter(u -> u.getId().equals(primaryKey))
                .findFirst()
                .isPresent();
    }

    @Override
    public UserEntity findByNickName(String nickName) {
        return userMemoryDatabase.stream()
                .filter(u -> u.getNickName().equals(nickName))
                .findFirst()
                .orElseThrow();
    }
}
