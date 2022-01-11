package com.kakao.cafe.user.service;

import com.kakao.cafe.user.dto.response.UserResDto;
import com.kakao.cafe.user.entity.UserEntity;
import com.kakao.cafe.user.dto.request.SignupReqDto;
import com.kakao.cafe.user.mapper.UserMapper;
import com.kakao.cafe.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void signup(SignupReqDto signupReqDto) {
        UserEntity userEntity = new UserEntity(signupReqDto.getUserId(), signupReqDto.getPassword(), signupReqDto.getName(), signupReqDto.getEmail());
        userRepository.save(userEntity);
    }

    public List<UserResDto> getUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        return userMapper.toUserResDtoList(userEntityList);
    }

    public UserResDto getUser(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId)
                .orElseThrow();

        return userMapper.toUserResDto(userEntity);
    }
}
