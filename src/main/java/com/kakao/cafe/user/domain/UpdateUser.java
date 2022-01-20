package com.kakao.cafe.user.domain;

import com.kakao.cafe.common.exception.UserUpdateException;
import org.mindrot.jbcrypt.BCrypt;

import javax.security.auth.login.LoginException;
import java.time.LocalDateTime;

public class UpdateUser {
    private final String password;

    public UpdateUser(String password) {
        this.password = password;
    }

    public void validatePassword(String plainPassword) throws UserUpdateException {
        if (!BCrypt.checkpw(plainPassword, password)) {
            throw new UserUpdateException();
        }
    }
}
