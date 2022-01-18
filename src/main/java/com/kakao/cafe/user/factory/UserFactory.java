package com.kakao.cafe.user.factory;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.SignUpDTO;
import com.kakao.cafe.user.dto.UpdateDTO;


public class UserFactory {

    private UserFactory() {
    }

    public static User toUser(Long id, SignUpDTO signUpDTO) {
        return new User(id, signUpDTO.getUserId(), signUpDTO.getPassword(), signUpDTO.getName(), signUpDTO.getEmail());
    }

    public static User toUser(SignUpDTO signUpDTO) {
        return new User(signUpDTO.getUserId(), signUpDTO.getPassword(), signUpDTO.getName(), signUpDTO.getEmail());
    }

    public static User toUser(UpdateDTO updateDTO) {
        return new User(updateDTO.getId(), updateDTO.getUserId(), updateDTO.getPassword(), updateDTO.getName(), updateDTO.getEmail());
    }
}
