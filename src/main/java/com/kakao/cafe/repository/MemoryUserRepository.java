package com.kakao.cafe.repository;

import com.kakao.cafe.User;
import com.kakao.cafe.dto.CreateUserDto;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class MemoryUserRepository implements UserRepository {

    private static final Map<UUID, User> store = new HashMap<>();

    @Override
    public User save(CreateUserDto createUserDto) {
        return new User(createUserDto);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }
}
