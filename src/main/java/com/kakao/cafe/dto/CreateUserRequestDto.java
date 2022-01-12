package com.kakao.cafe.dto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateUserRequestDto {

    private final String password;
    private final String nickname;
    private final String email;

    CreateUserRequestDto(String nickname, String email, String password) {
        validateCreateUser(nickname, email, password);
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    private void validateCreateUser(String nickname, String email, String password) {
        if (nickname == null || nickname.isEmpty()) {
            throw new IllegalArgumentException("Wrong nickname format");
        }
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Wrong email format");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Wrong password format");
        }
    }

    private boolean isValidEmail(String email) {
        final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.find();
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

}
