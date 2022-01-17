package com.kakao.cafe.user.application.port.in;

public class UserInventoryInfo {
    private final int index;
    private final String nickName;
    private final String name;
    private final String email;
    private final Long userId;

    public UserInventoryInfo(int index, long userId, String nickName, String name, String email) {
        this.index = index;
        this.userId = userId;
        this.nickName = nickName;
        this.name = name;
        this.email = email;
    }
}
