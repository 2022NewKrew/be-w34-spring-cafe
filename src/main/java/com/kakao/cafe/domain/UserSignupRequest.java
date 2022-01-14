package com.kakao.cafe.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserSignupRequest {

    @NotBlank(message = "아이디가 비어있습니다")
    @NotNull(message = "아이디가 null입니다")
    private final String userId;
    @NotBlank(message = "비밀번호가 비어있습니다")
    @NotNull(message = "비밀번호가 null입니다")
    private final String password;
    @NotBlank(message = "이름이 비어있습니다")
    @NotNull(message = "이름이 null입니다")
    private final String name;
    @NotBlank(message = "이메일이 비어있습니다")
    @NotNull(message = "이메일이 null입니다")
    @Email
    private final String email;

    public UserSignupRequest(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public User toEntity() {
        return new User(userId, password, name, email);
    }
}
