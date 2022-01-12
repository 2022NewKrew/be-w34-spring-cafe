package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.CreateUserDto;
import com.kakao.cafe.dto.FindUserDto;
import com.kakao.cafe.dto.UserDto;
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
        UserDto userDto = new UserDto(
            createUserDto.getEmail(),
            createUserDto.getNickname(),
            createUserDto.getPassword()
        );

        User user = new User(userDto);
        store.put(user.getUserId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(FindUserDto findUserDto) {
        return Optional.ofNullable(store.get(findUserDto.getUserId()));
    }

    @Override
    public List<User> getAll() {
        return store.values().stream().collect(Collectors.toUnmodifiableList());
    }
}
