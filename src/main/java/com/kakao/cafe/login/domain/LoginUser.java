package com.kakao.cafe.login.domain;

import com.kakao.cafe.common.BaseEntity;
import lombok.Getter;
import org.mindrot.jbcrypt.BCrypt;

import javax.security.auth.login.LoginException;
import java.time.LocalDateTime;

@Getter
public class LoginUser extends BaseEntity {
    private final String nickname;
    private final String password;

    public LoginUser(Long id, LocalDateTime createdTime, String nickname, String password) {
        super(id, createdTime);
        this.nickname = nickname;
        this.password = password;
    }

    public void validatePassword(String plainPassword) throws LoginException {
        if (!BCrypt.checkpw(plainPassword, password)) {
            throw new LoginException();
        }
    }
}
