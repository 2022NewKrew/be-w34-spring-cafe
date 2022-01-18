package com.kakao.cafe.web.user.form;

public class UserProfileInfo {
    private final String nickname;
    private final String email;

    public UserProfileInfo(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }
}
