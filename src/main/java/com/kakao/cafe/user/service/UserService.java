package com.kakao.cafe.user.service;

import com.kakao.cafe.exception.InvalidPasswordException;
import com.kakao.cafe.user.dto.response.UserResDto;
import com.kakao.cafe.user.entity.UserEntity;
import com.kakao.cafe.user.dto.request.UserFormReqDto;
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

    public void signup(UserFormReqDto userFormReqDto) {
        UserEntity userEntity = UserEntity.builder()
                .userId(userFormReqDto.getUserId())
                .password(userFormReqDto.getPassword())
                .name(userFormReqDto.getName())
                .email(userFormReqDto.getEmail())
                .build();

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

    public void updateUser(String userId, UserFormReqDto userFormReqDto) {
        UserEntity userEntity = userRepository.findByUserId(userId)
                .orElseThrow();

        userEntity.update(userFormReqDto.getPassword(), userFormReqDto.getName(), userFormReqDto.getEmail());
        userRepository.save(userEntity);
    }

    public void login(String userId, String password) {
        UserEntity userEntity = userRepository.findByUserId(userId)
                .orElseThrow();

        if (!userEntity.getPassword().equals(password)) {
            throw new InvalidPasswordException("잘못된 패스워드를 입력했습니다.");
        }
    }

}
