package com.kakao.cafe.presentation.dto.request;

import com.kakao.cafe.application.dto.command.UserAccountEnrollCommand;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class UserAccountEnrollRequest {

    @Email
    private final String email;
    @NotNull
    private final String username;
    @NotNull
    private final String password;

    public UserAccountEnrollCommand toCommand() {
        return new UserAccountEnrollCommand(
                password,
                username,
                email
        );
    }
}
