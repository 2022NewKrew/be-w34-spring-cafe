package com.kakao.cafe.user.domain;

public class UserFactory {
    private static Long SEQUENCE_NOT_NEEDED = 0L;

    public static User getUser(String userId, String password, String name, String email){
        return new User(userId, password, name, email, SEQUENCE_NOT_NEEDED);
    }
}
