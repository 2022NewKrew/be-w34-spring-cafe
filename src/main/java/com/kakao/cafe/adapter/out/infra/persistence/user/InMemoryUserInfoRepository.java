package com.kakao.cafe.adapter.out.infra.persistence.user;

import com.kakao.cafe.domain.user.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class InMemoryUserInfoRepository implements UserInfoRepository {

    private final Map<String, User> repository;

    public InMemoryUserInfoRepository() {
        repository = new HashMap<>();
    }

    public void save(User user) {
        repository.put(user.getUserId(), user);
    }

    public List<User> getAllUserList() {
        return new ArrayList<>(repository.values());
    }

    public Optional<User> findByUserId(String userId) {
        return Optional.ofNullable(repository.get(userId));
    }
}
