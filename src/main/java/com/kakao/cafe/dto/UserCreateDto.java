package com.kakao.cafe.dto;


import com.kakao.cafe.model.Article;
import com.kakao.cafe.model.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

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
    private Integer userSequence = 0;

    public UserCreateDto(String userId, String password, String userName, String email) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.email = email;
    }

    public User toEntity() {
        return User.builder().id(userSequence++)
                .userId(userId)
                .password(password)
                .userName(userName)
                .email(email)
                .build();
    }

}
