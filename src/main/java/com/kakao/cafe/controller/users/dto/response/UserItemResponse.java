package com.kakao.cafe.controller.users.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public class UserItemResponse {
    private final Integer seq;
    private final String userId;
    private final String userName;
    private final String email;

    @Builder
    public UserItemResponse(Integer seq, String userId, String userName, String email) {
        this.seq = seq;
        this.userId = userId;
        this.userName = userName;
        this.email = email;
    }
}
