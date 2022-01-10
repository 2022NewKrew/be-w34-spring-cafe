package com.kakao.cafe.users;

public class UserDto {
    private final String userId;
    private final String name;
    private final String email;

    public UserDto(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public static UserDto toDto(User user) {
        return new UserDto(user.getUserId(), user.getName(), user.getEmail());
    }
}
