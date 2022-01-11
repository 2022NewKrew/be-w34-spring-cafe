package com.kakao.cafe.repository;

import com.kakao.cafe.User;
import com.kakao.cafe.dto.CreateUserDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class MemoryUserRepository implements UserRepository {

    private static final Map<UUID, User> store = new HashMap<>();

    @Override
    public User save(CreateUserDto createUserDto) {
        User user = new User(createUserDto);
        store.put(user.getUserId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<User> getAll() {
        return store.values().stream().collect(Collectors.toUnmodifiableList());
    }
}
