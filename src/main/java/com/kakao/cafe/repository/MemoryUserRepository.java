package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserResponseDto;
import com.kakao.cafe.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MemoryUserRepository implements UserRepository{
    private final List<User> users = new ArrayList<>();
    private static long sequence = 0L;
    private final Logger logger = LoggerFactory.getLogger(MemoryUserRepository.class);

    @Override
    public void save(UserRequestDto userRequestDto) {
        User user = User.of(++sequence, userRequestDto.getUserId(), userRequestDto.getPassword(), userRequestDto.getName(), userRequestDto.getEmail());
        logger.info("save: {}, {}", user.getId(), user.getUserId());
        users.add(user);
    }

    @Override
    public void update(Long id, UserRequestDto userRequestDto) {
        User user = User.of(id, userRequestDto.getUserId(), userRequestDto.getPassword(), userRequestDto.getName(), userRequestDto.getEmail());
        users.set(id.intValue(), user);
    }

    @Override
    public Optional<UserResponseDto> findByUserId(String userId) {
        return users.stream()
                .filter(user -> user.getUserId().equals(userId))
                .map(user -> UserResponseDto.of(user.getId(), user.getUserId(), user.getName(), user.getEmail(), user.getCreatedAt()))
                .findFirst();
    }

    @Override
    public List<UserResponseDto> findAll() {
        return users.stream()
                .map(user -> UserResponseDto.of(user.getId(), user.getUserId(), user.getName(), user.getEmail(), user.getCreatedAt()))
                .collect(Collectors.toUnmodifiableList());
    }
}
