package com.kakao.cafe.domain;

public class SessionUser {
    private final int id;
    private final String userName;

    public SessionUser(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public static SessionUser from(User user) {
        return new SessionUser(user.getId(), user.getUserName());
    }
}
