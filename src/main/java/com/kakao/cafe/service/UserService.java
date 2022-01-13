package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.web.dto.UserCreateRequestDto;
import com.kakao.cafe.web.dto.UserResponseDto;
import com.kakao.cafe.web.dto.UserProfileResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(UserCreateRequestDto requestDto) {
        Long id = userRepository.generateId();
        userRepository.create(new User(id, requestDto.getUserId(), requestDto.getPassword(), requestDto.getName(), requestDto.getEmail()));
        logger.info("{} 계정 생성", requestDto.getUserId());
    }

    public List<UserResponseDto> findAll() {
        return UserResponseDto.from(userRepository.findAll());
    }

    public UserProfileResponseDto findByUserId(String userId) {
        UserProfileResponseDto responseDto = new UserProfileResponseDto();
        Optional<User> foundUser = userRepository.findByUserId(userId);
        if (foundUser.isPresent()) {
            responseDto.setUserId(foundUser.get().getUserId());
            responseDto.setEmail(foundUser.get().getEmail());
            return responseDto;
        }
        return null;
    }
}
