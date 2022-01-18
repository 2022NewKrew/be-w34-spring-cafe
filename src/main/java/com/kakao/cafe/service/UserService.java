package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserResponseDTO;
import com.kakao.cafe.dto.UserRequestDTO;
import com.kakao.cafe.dto.UserUpdateDTO;
import com.kakao.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void create(UserRequestDTO userRequestDto) {
        Optional<User> user = userRepository.findByUserId(userRequestDto.getUserId());
        if(user.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
        userRepository.save(userRequestDto);
    }

    public void update(UserUpdateDTO userUpdateDTO) {
        if(!userUpdateDTO.getPassword().equals(userUpdateDTO.getPasswordCheck())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        User user = userRepository.findByUserId(userUpdateDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        userRepository.update(userUpdateDTO, user.getId());
    }

    public Optional<UserResponseDTO> read(String userId) {
        Optional<User> userOptional = userRepository.findByUserId(userId);
        if(userOptional.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 유저입니다.");
        }
        User user = userOptional.get();
        return Optional.ofNullable(UserResponseDTO.of(user.getId(), user.getUserId(), user.getName(), user.getEmail(), user.getCreatedAt()));
    }

    public List<UserResponseDTO> readAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> UserResponseDTO.of(user.getId(), user.getUserId(), user.getName(), user.getEmail(), user.getCreatedAt()))
                .collect(Collectors.toUnmodifiableList());
    }
}
