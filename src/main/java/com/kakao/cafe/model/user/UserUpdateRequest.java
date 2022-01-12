package com.kakao.cafe.model.user;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserUpdateRequest {
    private Long id;
    private String email;
    private String nickName;
    private String password;
    private String currentPassword;

    @Builder
    public UserUpdateRequest(Long id, String email, String nickName, String password, String currentPassword) {
        this.id = id;
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.currentPassword = currentPassword;
    }
}
