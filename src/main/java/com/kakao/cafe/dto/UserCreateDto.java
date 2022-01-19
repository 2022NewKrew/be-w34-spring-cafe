package com.kakao.cafe.dto;

import com.kakao.cafe.model.User;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class UserCreateDto {

    @NotBlank(message = "id를 입력해주세요")
    private final String userId;
    @NotBlank(message = "비밀번호를 입력해주세요")
    private final String password;
    @NotBlank(message = "이름을 입력해주세요")
    private final String userName;
    @NotBlank(message = "이메일을 입력해주세요")
    private final String email;

    public UserCreateDto(String userId, String password, String userName, String email) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.email = email;
    }


    public User toEntity() {
        return User.builder().id(0)
                .userId(userId)
                .password(password)
                .userName(userName)
                .email(email)
                .build();
    }

}
