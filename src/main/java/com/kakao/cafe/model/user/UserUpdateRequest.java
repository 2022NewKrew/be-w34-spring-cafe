package com.kakao.cafe.model.user;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserUpdateRequest {
    @NotBlank(message = "이메일은 필수 입력 값 입니다.")
    @Email(message = "이메일이 형식이 맞지 않습니다.")
    private String email;
    @NotBlank(message = "닉네임은 필수 입력 값 입니다.")
    private String nickname;
    @NotBlank(message = "변경할 비밀번호는 필수 입력 값 입니다.")
    private String password;
    @NotBlank(message = "현재 비밀번호는 필수 입력 값 입니다.")
    private String currentPassword;

    @Builder
    public UserUpdateRequest(String email, String nickname, String password, String currentPassword) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.currentPassword = currentPassword;
    }
}
