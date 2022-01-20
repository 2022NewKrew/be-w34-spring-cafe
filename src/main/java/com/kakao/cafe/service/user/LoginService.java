package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;
import com.kakao.cafe.model.user.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public LoginService(@Qualifier("userJdbcRepositoryImpl") UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserDto login(String email, String password) {
        User savedUser = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일 입니다."));
        if (!password.equals(savedUser.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return modelMapper.map(savedUser, UserDto.class);
    }
}
