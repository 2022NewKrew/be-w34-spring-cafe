package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.dtos.UserResponseDto;
import com.kakao.cafe.domain.dtos.UserSaveDto;
import com.kakao.cafe.exception.NotFoundException;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(@Qualifier("userRepositoryJdbcImpl") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(UserSaveDto dto) {
        User newUser = new User(dto.getEmail(), dto.getName(), dto.getPassword());
        userRepository.save(newUser);
    }

    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id);
        return new UserResponseDto(user);
    }

    public void update(Long id, UserSaveDto dto) {
        User user = userRepository.findById(id);
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        userRepository.save(user);
    }
}
