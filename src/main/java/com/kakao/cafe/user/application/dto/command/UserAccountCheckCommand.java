package com.kakao.cafe.user.application.dto.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserAccountCheckCommand {

    private final String email;
    private final String password;

}
