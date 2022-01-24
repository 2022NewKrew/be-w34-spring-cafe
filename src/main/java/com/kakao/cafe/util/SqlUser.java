package com.kakao.cafe.util;

public enum SqlUser {
    INSERT_USER("INSERT INTO `USER`(email, nickname, password, createdAt) VALUES(:email, :nickname, :password, :createdAt)"),
    UPDATE_USER("UPDATE `USER` SET nickname=:nickname, password=:password WHERE id=:id"),
    FIND_USER_BY_ID("SELECT * FROM `USER` WHERE id = :id"),
    FIND_USER_BY_EMAIL("SELECT * FROM `USER` WHERE email = :email"),
    FIND_USER_BY_NICKNAME("SELECT * FROM `USER` WHERE nickname = :nickname"),
    FIND_ALL_USER("SELECT * FROM `USER`");

    private final String QUERY;

    SqlUser(String QUERY) {
        this.QUERY = QUERY;
    }

    public String query() {
        return QUERY;
    }
}
