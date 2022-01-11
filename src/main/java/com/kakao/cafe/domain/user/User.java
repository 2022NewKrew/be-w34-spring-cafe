package com.kakao.cafe.domain.user;

import com.kakao.cafe.exception.InvalidPasswordException;

public class User {

    private Long id;
    private String username;
    private String nickname;
    private String email;
    private String password;

    public User(UserCreateRequest request) {
        this.username = request.getUsername();
        this.nickname = request.getNickname();
        this.email = request.getEmail();
        this.password = request.getPassword();
    }

    public UserDto toDto() {
        return new UserDto(id, username, nickname, email);
    }

    public void update(UserUpdateRequest request) {
        validatePassword(request.getPassword());
        this.nickname = request.getNickname();
        this.email = request.getEmail();
        this.password = request.getPassword();
    }

    private void validatePassword(String newPassword) {
        if (!password.equals(newPassword)) {
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
        }
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
