package com.kakao.cafe.util;

public final class Sql {
    public static final String INSERT_USER = "INSERT INTO `USER`(email, nickname, password, createdAt) VALUES(:email, :nickname, :password, :createdAt)";
    public static final String UPDATE_USER = "UPDATE `USER` SET nickname=:nickname, password=:password WHERE id=:id";
    public static final String FIND_ALL_USER = "SELECT * FROM `USER`";
    public static final String FIND_USER_BY_ID = "SELECT * FROM `USER` WHERE id = :id";
    public static final String FIND_USER_BY_EMAIL = "SELECT * FROM `USER` WHERE email = :email";
    public static final String FIND_USER_BY_NICKNAME = "SELECT * FROM `USER` WHERE nickname = :nickname";
    public static final String INSERT_ARTICLE = "INSERT INTO `ARTICLE`(userId, title, body, createdAt) VALUES(:userId, :title, :body, :createdAt)";
    public static final String FIND_ALL_ARTICLE = "SELECT * FROM `ARTICLE`";
    public static final String FIND_ARTICLE_BY_ID = "SELECT * FROM `ARTICLE` WHERE id = :id";
}
