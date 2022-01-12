package com.kakao.cafe.model.User;

public class User {
    private Long id;
    private final String userId;
    private final String password;
    private final String nickname;
    private final String email;

    public User(UserCreateRequestDto request) {
        this.userId = request.getUserId();
        this.nickname = request.getNickname();
        this.email = request.getEmail();
        this.password = request.getPassword();
    }

    public UserResponseDto toResponseDto() {
        return new UserResponseDto(id, userId, nickname, email);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
