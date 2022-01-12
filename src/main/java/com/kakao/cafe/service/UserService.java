package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.repository.MemoryUserRepository;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.web.dto.UserCreateRequestDto;
import com.kakao.cafe.web.dto.UserListResponseDto;
import com.kakao.cafe.web.dto.UserProfileResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository = new MemoryUserRepository();

    public void create(UserCreateRequestDto requestDto) {
        Long id = userRepository.generateId();
        User user = userRepository.signUp(new User(id, requestDto.getUserId(), requestDto.getPassword(), requestDto.getName(), requestDto.getEmail()));
        logger.info("{} 계정 생성", user.getUserId());
    }

    public List<UserListResponseDto> findAll() {
        List<UserListResponseDto> responseDtoList = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            UserListResponseDto responseDto = new UserListResponseDto();
            responseDto.setId(user.getId());
            responseDto.setUserId(user.getUserId());
            responseDto.setName(user.getName());
            responseDto.setEmail(user.getEmail());
            responseDtoList.add(responseDto);
        }
        return responseDtoList;
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
