package com.kakao.cafe.service;

import com.kakao.cafe.controller.users.dto.UserItemDto;
import com.kakao.cafe.controller.users.dto.UserProfileDto;
import com.kakao.cafe.controller.users.mapper.UserDtoMapper;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(
            @Qualifier("memoryUserRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long signUp(String userId, String password, String userName, String email) {
        User signUpUser = User.of(userId, password, userName, email);
        return userRepository.insertUser(signUpUser);
    }

    public List<UserItemDto> getUsersAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserDtoMapper::toUserItemDto)
                .collect(Collectors.toList());
    }

    public UserProfileDto getUserProfile(String userId) {
        User findUser = userRepository.findByUserId(userId);
        return UserDtoMapper.toUserProfileDto(findUser);
    }

}
