package com.kakao.cafe.adapter.out.infra.persistence.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class UserInfoRepository {

    private final Map<String, UserInfoEntity> repository;

    public UserInfoRepository() {
        repository = new HashMap<>();
    }

    public void save(UserInfoEntity userInfoEntity) {
        repository.put(userInfoEntity.getUserId(), userInfoEntity);
    }

    public List<UserInfoEntity> getAllUserList() {
        return new ArrayList<>(repository.values());
    }

    public Optional<UserInfoEntity> findByUserId(String userId) {
        return Optional.of(repository.get(userId));
    }
}
