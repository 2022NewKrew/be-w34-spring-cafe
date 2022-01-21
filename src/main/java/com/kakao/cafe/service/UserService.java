package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserResponseDTO;
import com.kakao.cafe.dto.UserRequestDTO;
import com.kakao.cafe.dto.UserUpdateDTO;
import com.kakao.cafe.error.exception.InvalidPasswordException;
import com.kakao.cafe.error.exception.UserAlreadyExistException;
import com.kakao.cafe.error.exception.UserNotFoundException;
import com.kakao.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void create(UserRequestDTO userRequestDto) {
        Optional<User> user = userRepository.findByUserId(userRequestDto.getUserId());
        if(user.isPresent()) {
            throw new UserAlreadyExistException();
        }
        userRepository.save(userRequestDto);
    }

    @Transactional
    public void update(UserUpdateDTO userUpdateDTO) {
        if(!userUpdateDTO.getPassword().equals(userUpdateDTO.getPasswordCheck())) {
            throw new InvalidPasswordException();
        }
        User user = userRepository.findByUserId(userUpdateDTO.getUserId())
                .orElseThrow(UserNotFoundException::new);
        userRepository.update(userUpdateDTO, user.getId());
    }

    @Transactional(readOnly = true)
    public UserResponseDTO read(String userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(UserNotFoundException::new);
        return mapper(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> readAll() {
        return userRepository.findAll()
                .stream()
                .map(this::mapper)
                .collect(Collectors.toUnmodifiableList());
    }

    private UserResponseDTO mapper(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
