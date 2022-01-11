package com.kakao.cafe.domain;

public class User {

    protected final Long id;
    protected final String username;
    protected final String nickname;
    protected final String email;
    protected final String password;

    public User(UserRequest userRequest) {
        this.id = null;
        this.username = userRequest.getUsername();
        this.nickname = userRequest.getNickname();
        this.email = userRequest.getEmail();
        this.password = userRequest.getPassword();
    }

    private User(Long id, User user) {
        this.id = id;
        this.username = user.username;;
        this.nickname = user.nickname;
        this.email = user.email;
        this.password = user.password;
    }

    public User setId(Long id) {
        return new User(id, this);
    }

    public UserDto toDto() {
        return new UserDto(id, username, nickname, email);
    }

    public boolean isEqualUserId(long id) {
        return this.id == id;
    }

}
