package com.kakao.cafe.user.adapter.in.web.dto.request;

import com.kakao.cafe.user.application.dto.command.UserAccountEnrollCommand;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
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
