package com.kakao.cafe.user.application.dto.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserAccountDetailEmailCommand {

    private final String email;

}
