package com.kakao.cafe.user;

public class UserCreationForm {
    private final String email;
    private final String username;
    private final String password;
    private final String displayName;

    public UserCreationForm(String email, String username, String password, String displayName) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDisplayName() {
        return displayName;
    }
}
