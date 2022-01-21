package com.kakao.cafe.domain;

public class SessionUser {
    private final int id;

    public SessionUser(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static SessionUser from(User user) {
        return new SessionUser(user.getId());
    }
}
