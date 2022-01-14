package com.kakao.cafe.adapter.out.infra.persistence.user;

import com.kakao.cafe.domain.user.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<UserVO> getAllUserList() {
        return repository.values().stream().map(UserVO::from).collect(Collectors.toList());
    }

    public Optional<UserVO> findByUserId(String userId) {
        return Optional.of(UserVO.from(repository.get(userId)));
    }
}
