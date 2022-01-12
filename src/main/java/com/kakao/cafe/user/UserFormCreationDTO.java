package com.kakao.cafe.user;

public class UserFormCreationDTO {
    private final String username;
    private final String password;
    private final String email;

    public UserFormCreationDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
