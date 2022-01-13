package com.kakao.cafe.user;

import java.util.Arrays;

public enum UserStatus {
    USER("USER"), ADMIN("ADMIN");

    private final String role;

    UserStatus(String role) {
        this.role = role;
    }

    public static String convertToDataBase(UserStatus userStatus) {
        return userStatus.role;
    }
}
