package com.kakao.cafe.user.domain;

import com.kakao.cafe.common.exception.AuthenticationException;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

import static com.kakao.cafe.common.exception.ExceptionMessage.REQUIRED_RE_LOGIN_EXCEPTION;

@Builder
@Getter
public class SessionedUser {
    private String userId;
    private String password;
    private String name;
    private String email;

    public static SessionedUser valueOf(User user) {
        return SessionedUser.builder()
                .userId(user.getUserId())
                .password(user.getPassword())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public void validateSession(String userId) throws AuthenticationException {
        if (!Objects.equals(userId, this.userId)) {
            AuthenticationException.throwAuthFailure(REQUIRED_RE_LOGIN_EXCEPTION);
        }
    }

    public boolean matchesPassword(String password) {
        return Objects.equals(password, this.password);
    }
}
