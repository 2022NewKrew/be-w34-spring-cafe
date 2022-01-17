package com.kakao.cafe.constant.sql;

public enum ArticleSql {
    FIND_ALL("SELECT * FROM ARTICLE"),
    FIND_BY_ID("SELECT * FROM ARTICLE WHERE ID =:primaryKey");

    private String query;

    ArticleSql(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
