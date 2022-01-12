package com.kakao.cafe.Service;

import com.kakao.cafe.Repository.UserRepository;
import com.kakao.cafe.model.User.User;
import com.kakao.cafe.model.User.UserCreateRequestDto;
import com.kakao.cafe.model.User.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserResponseDto> getUserList() {
        return userRepository.getUserList().stream()
                .map(User::toResponseDto)
                .collect(Collectors.toList());
    }

    public void signUp(UserCreateRequestDto user) {
        userRepository.add(user.toUser());
    }

    public UserResponseDto findUserById(Long id) {
        return userRepository.getUserList().stream()
                .filter(u -> id.equals(u.getId()))
                .collect(Collectors.toList())
                .get(0)
                .toResponseDto();
    }
}
