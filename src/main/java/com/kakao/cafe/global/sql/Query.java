package com.kakao.cafe.global.sql;

public class Query {

    private StringBuilder sb = new StringBuilder();

    public Query SELECT_FROM(TableName table) {
        sb.append("SELECT * FROM").append(table);
        return this;
    }

    public Query WHERE(String condition, String value) {
        sb.append(" WHERE ").append(condition).append("=").append(value);
        return this;
    }

    public String build() {
        return sb.toString();
    }
}
