package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserCreateDto;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(@Qualifier("userRepositoryJDBC") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(UserCreateDto userCreateDto) {
        User newUser = User.of(userCreateDto);

        // 아이디 중복 체크
        if (userRepository.findByUserId(newUser.getUserId()) != null)
            throw new IllegalArgumentException("member id duplicate");

        // 이메일 중복 체크
        if (userRepository.findByEmail(newUser.getEmail()) != null)
            throw new IllegalArgumentException("member email duplicate");

        userRepository.save(newUser);
    }

    @Override
    public UserDto getUser(String userId) {
        return UserDto.of(userRepository.findByUserId(userId));
    }

    @Override
    public List<UserDto> getUserList() {
        Optional<List<User>> userList = userRepository.findAll();

        return userList.map(users -> users
                .stream()
                .map(UserDto::of)
                .collect(Collectors.toList())).orElse(null);
    }
}
