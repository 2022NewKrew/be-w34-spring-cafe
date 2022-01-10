package com.kakao.cafe.user.model;

public class User {

    private final Long id;
    private final String userId;
    private final String name;
    private final String password;
    private final String email;

    public User(Long id, UserRequest userRequest) {
        this.id = id;
        this.userId = userRequest.getUserId();
        this.name = userRequest.getName();
        this.password = userRequest.getPassword();
        this.email = userRequest.getEmail();
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public UserDto toUserDto(){
        return new UserDto(id, userId, name, email);
    }

    public UserProfileDto toProfileDto(){
        return new UserProfileDto(name, email);
    }
}
