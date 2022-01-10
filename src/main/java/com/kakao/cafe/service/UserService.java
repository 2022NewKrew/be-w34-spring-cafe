package com.kakao.cafe.service;

import com.kakao.cafe.dto.response.UserResDto;
import com.kakao.cafe.entity.UserEntity;
import com.kakao.cafe.dto.request.SignupReqDto;
import com.kakao.cafe.mapper.UserMapper;
import com.kakao.cafe.repository.UserRepository;
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
}
