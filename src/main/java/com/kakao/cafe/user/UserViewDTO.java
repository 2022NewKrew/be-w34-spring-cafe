package com.kakao.cafe.user;

public class UserViewDTO {
    private final String username;
    private final String email;

    public UserViewDTO(String username, String email) {
        this.username = username;
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserViewDTO{" + "username='" + username + '\'' + ", email='" + email + '\'' + '}';
    }
}
