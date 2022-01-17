package com.kakao.cafe.user.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User implements Serializable {

    private Long id;
    private String email;
    private String password;
    private String nickname;
    private LocalDateTime createdAt;

    public void signup(UserValidator userValidator) {
        userValidator.validate(this);
    }
}
