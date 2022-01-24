package com.kakao.cafe.util;

public enum SqlReply {
    INSERT_REPLY("INSERT INTO `REPLY`(userId, articleId, comments, createdAt) VALUES (:userId, :articleId, :comments, :createdAt)"),
    DELETE_REPLY("DELETE FROM `REPLY` WHERE id = :id"),
    DELETE_REPLY_BY_ARTICLEID("DELETE FROM `REPLY` WHERE articleId = :articleId"),
    FIND_ALL_REPLY("SELECT * FROM `REPLY` WHERE articleId = :articleId"),
    FIND_NICKNAME_BY_USERID("SELECT USER.nickname FROM `REPLY` JOIN `USER` ON USER.id = REPLY.userId WHERE REPLY.id = :id");

    private final String QUERY;

    SqlReply(String QUERY) {
        this.QUERY = QUERY;
    }

    public String query() {
        return QUERY;
    }
}
