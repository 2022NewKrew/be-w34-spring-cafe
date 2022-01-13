package com.kakao.cafe.user.factory;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.SignUpDTO;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    public User of(Long id, SignUpDTO signUpDTO) {
        return new User(id, signUpDTO);
    }
}
