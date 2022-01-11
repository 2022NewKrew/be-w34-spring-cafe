package com.kakao.cafe.domain.user;

public class UserDto {

    private final Long id;
    private final String username;
    private final String nickname;
    private final String email;

    protected UserDto(Long id, String username, String nickname, String email) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.email = email;
    }
}
