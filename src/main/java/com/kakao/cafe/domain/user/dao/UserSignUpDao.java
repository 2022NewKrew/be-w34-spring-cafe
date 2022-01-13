package com.kakao.cafe.domain.user.dao;

import com.kakao.cafe.domain.user.dto.UserSignUpDto;
import com.kakao.cafe.domain.user.exception.UserAlreadyExistException;
import org.springframework.stereotype.Service;

@Service
public class UserSignUpDao {

    private final UserRepository userRepository;

    public UserSignUpDao(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(UserSignUpDto dto) {
        userRepository.findByUserId(dto.getUserId())
                .ifPresent(obj -> {
                    throw new UserAlreadyExistException(obj.getUserId());
                });
        userRepository.save(dto.toEntity());
    }
}
