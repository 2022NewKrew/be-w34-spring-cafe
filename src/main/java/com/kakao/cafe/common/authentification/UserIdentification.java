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

    public static UserIdentification getIdFromSession(HttpSession httpSession) {
        Object value = httpSession.getAttribute("sessionedUser");
        validateSignIn(value);
        return (UserIdentification) value;
    }

    private static void validateSignIn(Object value) {
        if(!(value instanceof UserIdentification)) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }
    }

}
