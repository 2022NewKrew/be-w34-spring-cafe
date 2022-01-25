package com.kakao.cafe.request;

import com.kakao.cafe.domain.User;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserSignupRequest {

    private static final int INITIAL_ID = 0;
    @NotBlank(message = "아이디가 비어있습니다")
    private final String userId;
    @NotBlank(message = "비밀번호가 비어있습니다")
    private final String password;
    @NotBlank(message = "이름이 비어있습니다")
    private final String name;
    @NotBlank(message = "이메일이 비어있습니다")
    @Email
    private final String email;

    public UserSignupRequest(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public User toEntity() {
        return new User(INITIAL_ID, userId, password, name, email);
    }
}
