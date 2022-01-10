package com.kakao.cafe.domain.user;

import com.kakao.cafe.domain.user.dto.UserCreateRequestDto;
import com.kakao.cafe.domain.user.dto.UserResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDto> retrieveAllUsers() {
        return userRepository.findAll().stream().map(UserResponseDto::new).collect(Collectors.toList());
    }

    public UserResponseDto retrieveUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
        return new UserResponseDto(user);
    }

    public Long createUser(UserCreateRequestDto requestDto) {
        logger.info("유저 생성 User ID : {}, Email : {}, Name : {}", requestDto.getUserId(), requestDto.getEmail(), requestDto.getName());
        return userRepository.save(requestDto.toUser());
    }
}
