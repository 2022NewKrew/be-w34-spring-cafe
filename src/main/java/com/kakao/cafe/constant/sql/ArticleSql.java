package com.kakao.cafe.constant.sql;

public enum ArticleSql {
    FIND_ALL("SELECT * FROM ARTICLE"),
    FIND_BY_ID("SELECT * FROM ARTICLE WHERE ID =:primaryKey"),
    UPDATE_VIEWS_BY_ID("UPDATE ARTICLE SET VIEWS =:views WHERE ID =:primaryKey"),
    DELETE_BY_ID("DELETE FROM ARTICLE WHERE ID =:primaryKey"),
    UPDATE_BY_ID("UPDATE ARTICLE SET TITLE =:title, CONTENT =:content WHERE ID =:primaryKey");

    private String query;

    ArticleSql(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
