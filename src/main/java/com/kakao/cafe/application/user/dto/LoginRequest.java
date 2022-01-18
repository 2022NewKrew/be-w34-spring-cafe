package com.kakao.cafe.application.user.dto;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.exceptions.WrongPasswordException;

public class LoginRequest {

    final private String userId;
    final private String password;

    public LoginRequest(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public void checkPassword(User user) throws WrongPasswordException {
        if (!user.equalsPassword(this.password)) {
            throw new WrongPasswordException("패스워드가 잘못 되었습니다.");
        }
    }
}
