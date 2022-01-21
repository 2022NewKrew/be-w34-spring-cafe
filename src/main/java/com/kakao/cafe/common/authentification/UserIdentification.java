package com.kakao.cafe.common.authentification;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.servlet.http.HttpSession;

@Getter
@ToString
public class UserIdentification {

    private final String userId;
    private final String userName;
    private final String email;

    @Builder
    public UserIdentification(String userId, String userName, String email) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
    }

    public static UserIdentification of(String userId, String userName, String email) {
        return UserIdentification.builder()
                .userId(userId)
                .userName(userName)
                .email(email)
                .build();
    }

    public boolean matchesUserId(String userId){
        return this.userId.equals(userId);
    }

}
