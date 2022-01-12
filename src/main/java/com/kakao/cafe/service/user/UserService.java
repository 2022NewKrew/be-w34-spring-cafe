package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;
import com.kakao.cafe.model.user.UserDto;
import com.kakao.cafe.model.user.UserSignupRequest;
import com.kakao.cafe.model.user.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public void signupUser(UserSignupRequest user) {
        userRepository.save(User.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .password(user.getPassword())
                .createdAt(LocalDateTime.now())
                .build());
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public UserDto getUserById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));
        return modelMapper.map(user, UserDto.class);
    }

    public void updateUser(long id, UserUpdateRequest request) {
        User savedUser = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));
        if (!request.getCurrentPassword().equals(savedUser.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        userRepository.update(User.builder()
                .id(id)
                .email(request.getEmail())
                .nickname(request.getNickName())
                .password(request.getPassword())
                .createdAt(savedUser.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build()
        );
    }
}
