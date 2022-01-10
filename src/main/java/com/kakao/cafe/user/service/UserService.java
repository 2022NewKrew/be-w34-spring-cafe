package com.kakao.cafe.user.service;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.repository.UserRepository;
import com.kakao.cafe.user.web.dto.UserSaveDto;
import com.kakao.cafe.user.web.dto.UserShowDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void addUser(UserSaveDto userSaveDto) {
        User user = User.builder()
            .userId(userSaveDto.getUserId())
            .name(userSaveDto.getName())
            .email(userSaveDto.getEmail()).password(userSaveDto.getPassword())
            .build();
        userRepository.save(user);
    }

    public List<UserShowDto> findAllUser() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
            .map(this::createUserShowDto)
            .collect(Collectors.toList());
    }

    private UserShowDto createUserShowDto(User user) {
        return UserShowDto.builder()
            .name(user.getName())
            .email(user.getEmail())
            .userId(user.getUserId())
            .build();
    }

    public UserShowDto findUser(String userId) {
        return userRepository.find(userId)
            .map(this::createUserShowDto)
            .orElseThrow(() -> new IllegalArgumentException("invalid userId"));
    }


}
