package com.kakao.cafe.service.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class UserInfo {
    private final Long id;
    private final String userId;
    private final String userName;
    private final String email;
}
