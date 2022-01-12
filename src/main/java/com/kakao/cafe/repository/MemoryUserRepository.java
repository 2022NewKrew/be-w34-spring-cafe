package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserResponseDto;
import com.kakao.cafe.dto.UserRequestDto;
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
    public void save(UserRequestDto userRequestDto) {
        User user = User.of(userRequestDto.getUserId(), userRequestDto.getPassword(), userRequestDto.getName(), userRequestDto.getEmail());
        user.setId(++sequence);
        logger.info("save: {}, {}", user.getId(), user.getUserId());
        users.add(user);
    }

    @Override
    public void update(Long id, UserRequestDto userRequestDto) {
        User user = User.of(userRequestDto.getUserId(), userRequestDto.getPassword(), userRequestDto.getName(), userRequestDto.getEmail());
        users.set(id.intValue(), user);
    }

    @Override
    public Optional<UserResponseDto> findByUserId(String userId) {
        return users.stream()
                .filter(user -> user.getUserId().equals(userId))
                .map(user -> UserResponseDto.of(user.getId(), user.getUserId(), user.getName(), user.getEmail()))
                .findFirst();
    }

    @Override
    public List<UserResponseDto> findAll() {
        return users.stream()
                .map(user -> UserResponseDto.of(user.getId(), user.getUserId(), user.getName(), user.getEmail()))
                .collect(Collectors.toUnmodifiableList());
    }
}
