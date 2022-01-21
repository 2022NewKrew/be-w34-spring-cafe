package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.AuthDTO;
import com.kakao.cafe.dto.UserResponseDTO;
import com.kakao.cafe.error.exception.InvalidPasswordException;
import com.kakao.cafe.error.exception.UserNotFoundException;
import com.kakao.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserResponseDTO login(AuthDTO authDto) {
        Optional<User> userOptional = userRepository.findByUserId((authDto.getUserId()));
        User user = userOptional.orElseThrow(UserNotFoundException::new);
        if(!user.validatePassword(authDto.getPassword())) {
            throw new InvalidPasswordException();
        }
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
