package com.kakao.cafe.user.application.dto.result;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserAccountDetailResult {

    private final Long userAccountId;
    private final String username;
    private final String email;
    private final String password;
    private final String createdAt;

}
