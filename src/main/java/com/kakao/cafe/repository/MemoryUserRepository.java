package com.kakao.cafe.repository;

import com.kakao.cafe.controller.UserController;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MemoryUserRepository implements UserRepository{
    private final List<User> users;
    private static long sequence = 0L;
    private final Logger logger = LoggerFactory.getLogger(MemoryUserRepository.class);

    public MemoryUserRepository() {
        this.users = new ArrayList<>();
    }

    @Override
    public UserDto save(UserDto userDto) {
        User user = User.of(userDto.getUserId(), userDto.getPassword(), userDto.getName(), userDto.getEmail());
        user.setId(++sequence);
        logger.info("save: {}, {}", user.getId(), user.getUserId());
        users.add(user);
        return userDto;
    }

    @Override
    public Optional<UserDto> findByUserId(String userId) {
        return users.stream()
                .filter(user -> user.getUserId().equals(userId))
                .map(user -> user.exportDto())
                .findFirst();
    }

    @Override
    public List<UserDto> findAll() {
        return users.stream()
                .map(user -> user.exportDto())
                .collect(Collectors.toUnmodifiableList());
    }
}
