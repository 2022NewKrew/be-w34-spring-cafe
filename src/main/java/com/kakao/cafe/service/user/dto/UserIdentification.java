package com.kakao.cafe.service.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserIdentification {
    private final Long id;
    private final String userId;
    private final String userName;
    private final String email;

    @Builder
    public UserIdentification(Long id, String userId, String userName, String email) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.email = email;
    }
}
