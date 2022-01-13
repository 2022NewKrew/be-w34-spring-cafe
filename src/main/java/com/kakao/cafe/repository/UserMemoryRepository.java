package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.user.SignUpDTO;
import com.kakao.cafe.dto.user.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserMemoryRepository implements UserRepository{
    private static Map<Long, User> userMap = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public UserDTO save(SignUpDTO signUpDTO) {
        User user = new User(++sequence, signUpDTO);
        userMap.put(user.getId(), user);
        return new UserDTO(user.getId(), user.getUserId(), user.getName(), user.getEmail());
    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        User user = userMap.get(id);
        UserDTO userDTO = new UserDTO(user.getId(), user.getUserId(), user.getName(), user.getEmail());
        return Optional.ofNullable(userDTO);
    }

    @Override
    public Optional<UserDTO> findByUserId(String userId) {
        return userMap.values().stream()
                .filter(user -> userId.equals(user.getUserId()))
                .map(user -> new UserDTO(user.getId(), user.getUserId(), user.getName(), user.getEmail()))
                .findAny();
    }

    @Override
    public List<UserDTO> findAll() {
        return new ArrayList<>(
                userMap.values().stream()
                        .map(user -> new UserDTO(user.getId(), user.getUserId(), user.getName(), user.getEmail()))
                        .collect(Collectors.toList()));
    }
}