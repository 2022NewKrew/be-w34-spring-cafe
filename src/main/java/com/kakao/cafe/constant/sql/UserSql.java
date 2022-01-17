package com.kakao.cafe.constant.sql;

public enum UserSql {
    FIND_ALL("SELECT * FROM USERS"),
    FIND_BY_ID("SELECT * FROM USERS WHERE ID =:primaryKey"),
    FIND_BY_NICKNAME("SELECT * FROM USERS WHERE NICKNAME =:nickname"),
    FIND_BY_EMAIL("SELECT * FROM USERS WHERE EMAIL =:email");

    private String query;

    UserSql(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
