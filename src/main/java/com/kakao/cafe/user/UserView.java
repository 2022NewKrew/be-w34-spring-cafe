package com.kakao.cafe.user;

public class UserView {
    private final String email;
    private final String username;
    private final String displayName;

    public UserView(String email, String username, String displayName) {
        this.email = email;
        this.username = username;
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "UserView{" + "email='" + email + '\'' + ", username='" + username + '\'' + ", displayName='" + displayName + '\'' + '}';
    }
}
