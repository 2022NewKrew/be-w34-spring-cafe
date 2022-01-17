package com.kakao.cafe.adapter.out.infra.persistence.user;

import com.kakao.cafe.domain.user.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryUserInfoRepository implements UserInfoRepository {

    private final Map<String, User> repository;

    public InMemoryUserInfoRepository() {
        repository = new HashMap<>();
    }

    public void save(User user) {
        repository.put(user.getUserId(), user);
    }

    public void update(User user) {
        save(user);
    }

    public List<User> getAllUserList() {
        return new ArrayList<>(repository.values());
    }

    public Optional<User> findByUserId(String userId) {
        return Optional.of(repository.get(userId));
    }
}
