package com.kakao.cafe.application.user.dto;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.exceptions.WrongPasswordException;

public class UpdateRequest {

    private final String password;
    private final String name;
    private final String email;

    public UpdateRequest(String password, String name, String email) {
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void checkPassword(User user) throws WrongPasswordException {
        if (!user.equalsPassword(this.password)) {
            throw new WrongPasswordException("패스워드가 잘못 되었습니다.");
        }
    }
}
