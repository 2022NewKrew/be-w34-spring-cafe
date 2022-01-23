package com.kakao.cafe.constant.sql;

public enum ReplySql {
    DELETE_BY_ID("DELETE FROM REPLY WHERE ID =:primaryKey"),
    FIND_BY_ARTICLE_ID("SELECT * FROM REPLY WHERE ARTICLE_ID =:articleId");

    private String query;

    ReplySql(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
