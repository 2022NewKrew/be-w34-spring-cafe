package com.kakao.cafe.dto;

import com.kakao.cafe.model.User;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRegisterRequest {

    @NotBlank(message = "아이디는 빈 칸일 수 없습니다.")
    @Size(min = 2, max = 10, message = "아이디는 2-10자 이어야 합니다.")
    private final String userId;

    @NotBlank(message = "비밀번호는 빈 칸일 수 없습니다.")
    @Size(min = 10, max = 20, message = "비밀번호는 10-20자 이어야 합니다.")
    private final String password;

    @NotBlank(message = "이름은 빈 칸일 수 없습니다.")
    @Size(min = 1, max = 10, message = "이름은 1-10자 이어야 합니다.")
    private final String name;

    @NotBlank(message = "이메일은 빈 칸일 수 없습니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private final String email;

    public UserRegisterRequest(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getUserId() {
        return userId;
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

    public User toEntity() {
        return new User.Builder(userId, password, name, email).build();
    }
}
