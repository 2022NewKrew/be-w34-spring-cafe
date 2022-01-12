package com.kakao.cafe.member.util;

public class PasswordChecker {

    public static boolean checkPassword(String password, String passwordCheck) {
        return password.equals(passwordCheck);
    }
}
