package com.kakao.cafe.domain;

public class User {

    private final Long id;
    private final String username;
    private final String nickname;
    private final String email;
    private final String password;

    public User(Long id, UserRequest userRequest) {
        this.id = id;
        this.username = userRequest.getUsername();
        this.nickname = userRequest.getNickname();
        this.email = userRequest.getEmail();
        this.password = userRequest.getPassword();
    }

    public UserDto toDto() {
        return new UserDto(id, username, nickname, email);
    }
}
