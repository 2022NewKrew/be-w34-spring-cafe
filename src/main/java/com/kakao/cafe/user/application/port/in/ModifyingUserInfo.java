package com.kakao.cafe.user.application.port.in;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ModifyingUserInfo {
    @NotEmpty(message = "아이디는 필수사항입니다.")
    @Size(min = 3, max = 15, message = "아이디는 3글자 이상 15글자 이하입니다.")
    private final String nickname;

    @NotEmpty(message = "패스워드는 필수사항입니다.")
    private final String password;

    @NotEmpty(message = "이름은 필수사항입니다.")
    @Size(min = 2, max = 10, message = "이름은 두글자 이상 열글자 이하입니다.")
    private final String name;

    @NotEmpty(message = "이메일은 필수사항입니다.")
    @Email(message = "이메일 형식에 맞춰주세요.")
    private final String email;

    public ModifyingUserInfo(String nickname, String password, String name, String email) {
        this.nickname = nickname;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
