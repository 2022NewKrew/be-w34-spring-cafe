package com.kakao.cafe.util;

public enum SqlArticle {
    INSERT_ARTICLE("INSERT INTO `ARTICLE`(userId, title, body, createdAt) VALUES(:userId, :title, :body, :createdAt)"),
    DELETE_ARTICLE("DELETE FROM `ARTICLE` where id = :id"),
    UPDATE_ARTICLE_VIEW("UPDATE `ARTICLE` SET views=:views WHERE id=:id"),
    FIND_ARTICLE_BY_ID("SELECT * FROM `ARTICLE` WHERE id = :id"),
    FIND_UID_BY_ID("SELECT userId FROM `ARTICLE` WHERE id = :id"),
    FIND_NICKNAME_BY_USERID("SELECT USER.nickname FROM `ARTICLE` JOIN `USER` ON USER.id = ARTICLE.userId WHERE ARTICLE.id = :id"),
    FIND_ALL_ARTICLE("SELECT * FROM `ARTICLE`"),
    UPDATE_ARTICLE("UPDATE `ARTICLE` SET title=:title, body=:body WHERE id=:id");

    private final String QUERY;

    SqlArticle(String QUERY) {
        this.QUERY = QUERY;
    }

    public String query() {
        return QUERY;
    }
}
