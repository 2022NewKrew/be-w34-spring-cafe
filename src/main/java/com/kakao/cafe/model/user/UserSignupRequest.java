package com.kakao.cafe.model.user;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSignupRequest {
    private String email;
    private String nickname;
    private String password;

    @Builder
    public UserSignupRequest(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }
}
