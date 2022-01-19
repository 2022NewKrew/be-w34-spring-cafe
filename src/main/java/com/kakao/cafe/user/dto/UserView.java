package com.kakao.cafe.user.dto;

public class UserView {
    private final String username;
    private final String email;
    private final String displayName;

    public UserView(String username, String email, String displayName) {
        this.username = username;
        this.email = email;
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "UserView{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
