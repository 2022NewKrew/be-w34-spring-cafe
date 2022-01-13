package com.kakao.cafe.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateUserDto {
    @NotBlank(message = "아이디를 입력하세요")
    private String userId;

    @NotBlank(message = "패스워드를 입력하세요")
    private String password;

    @NotBlank(message = "이름을 입력하세요")
    private String name;

    @NotBlank(message = "이메일을 입력하세요")
    private String email;

    @Builder
    public CreateUserDto(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "CreateUserDto{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
