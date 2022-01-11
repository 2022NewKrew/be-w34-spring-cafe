package com.kakao.cafe.user.dto;

import com.kakao.cafe.user.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@RequiredArgsConstructor
public class SignUpReq {

    @NotBlank(message = "ID를 입력해주세요")
    private final String userId;

    @NotBlank(message = "패스워드를 입력해주세요")
    private final String password;

    @NotBlank(message = "이름을 입력해주세요")
    private final String name;

    @Email
    private final String email;

    public User toEntity() {
        return User.of(userId, password, name, email);
    }
}
