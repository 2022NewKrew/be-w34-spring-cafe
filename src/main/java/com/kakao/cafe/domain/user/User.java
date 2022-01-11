package com.kakao.cafe.domain.user;

public class User {

    private Long id;
    private String username;
    private String nickname;
    private String email;
    private String password;

    public User(UserRequest userRequest) {
        this.username = userRequest.getUsername();
        this.nickname = userRequest.getNickname();
        this.email = userRequest.getEmail();
        this.password = userRequest.getPassword();
    }

    public UserDto toDto() {
        return new UserDto(id, username, nickname, email);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEqualUserId(long id) {
        return this.id == id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            return this.id == ((User) obj).id;
        }
        return false;
    }
}
