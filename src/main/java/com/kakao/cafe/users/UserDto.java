package com.kakao.cafe.users;

public class UserDto {

    private final String userId;

    private final String name;

    private final String email;

    public UserDto(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
