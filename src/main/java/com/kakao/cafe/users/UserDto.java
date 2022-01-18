package com.kakao.cafe.users;

public class UserDto {
    private final Long id;
    private final String userId;
    private final String name;
    private final String email;

    public UserDto(Long id, String userId, String name, String email) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public static UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getUserId(), user.getName(), user.getEmail());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUserId() {
        return userId;
    }
}
