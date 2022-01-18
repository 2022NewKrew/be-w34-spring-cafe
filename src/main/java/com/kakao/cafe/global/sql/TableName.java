package com.kakao.cafe.global.sql;

public enum TableName {
    USER("users"),
    ARTICLE("articles");

    private String name;

    public String getName() {
        return name;
    }

    TableName(String name) {
        this.name = name;
    };


}
