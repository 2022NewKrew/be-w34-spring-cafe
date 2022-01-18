package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.AuthDTO;
import com.kakao.cafe.dto.UserResponseDTO;
import com.kakao.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    public UserResponseDTO login(AuthDTO authDto) {
        Optional<User> userOptional = userRepository.findByUserId((authDto.getUserId()));
        User user = userOptional.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        if(!user.validatePassword(authDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return UserResponseDTO.of(user.getId(), user.getUserId(), user.getName(), user.getEmail(), user.getCreatedAt());
    }
}
