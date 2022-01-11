package com.kakao.cafe.dto.user;

public class UserShowForm {
    private final int index;
    private final String nickName;
    private final String name;
    private final String email;
    private final Long userId;

    public UserShowForm(int index, long userId, String nickName, String name, String email) {
        this.index = index;
        this.userId = userId;
        this.nickName = nickName;
        this.name = name;
        this.email = email;
    }
}
