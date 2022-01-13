package com.kakao.cafe.controller.users.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class UserProfileResponse {
    private final String userName;
    private final String email;
}
