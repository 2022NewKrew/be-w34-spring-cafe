package com.kakao.cafe.domain.user;

import java.util.regex.Pattern;

public class Email {

    private final String email;

    public Email(String email) {
        if (email == null)
            throw new IllegalArgumentException("잘못된 입력입니다!");
        email = email.trim();
        if (email.length() == 0)
            throw new IllegalArgumentException("잘못된 입력입니다!");
        String pattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
        if (!Pattern.matches(pattern, email))
            throw new IllegalArgumentException("잘못된 입력입니다!");
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Email{" +
                "email='" + email + '\'' +
                '}';
    }
}
