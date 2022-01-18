package com.kakao.cafe.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class UserInfoResponse {

    private final Long id;
    private final String userId;
    private final String name;
    private final String email;
    private final LocalDateTime createdAt;
}
