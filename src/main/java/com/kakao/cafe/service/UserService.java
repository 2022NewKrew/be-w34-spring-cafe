package com.kakao.cafe.service;

import com.kakao.cafe.UserEntity;
import com.kakao.cafe.dto.request.SignupReqDto;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signup(SignupReqDto signupReqDto) {
        UserEntity userEntity = new UserEntity(signupReqDto.getId(), signupReqDto.getPassword(), signupReqDto.getName(), signupReqDto.getEmail());
        userRepository.save(userEntity);
    }
}
