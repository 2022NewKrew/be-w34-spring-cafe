package com.kakao.cafe.persistence.user.h2;

import java.util.List;
import java.util.stream.Collectors;

public class H2UserQueryBuilder {
    private static final String TABLE_NAME = "USER";
    private static final List<String> FIELDS = List.of("user_id", "user_password", "user_name", "user_email");
    private static final String SELECT_ALL;
    private static final String SELECT_BY_ONE_FIELD;

    static {
        SELECT_ALL = String.valueOf(new StringBuilder("SELECT ")
                .append(String.join(", ", FIELDS))
                .append(" FROM ")
                .append(TABLE_NAME));
        SELECT_BY_ONE_FIELD = String.valueOf(new StringBuilder("SELECT ")
                .append(String.join(", ", FIELDS))
                .append(" FROM ")
                .append(TABLE_NAME)
                .append(" WHERE ")
        );
    }

    private H2UserQueryBuilder() {
    }

    public static String selectAll() {
        return SELECT_ALL;
    }

    public static String selectOneByField(String field) {
        return String.valueOf(
                new StringBuilder(SELECT_BY_ONE_FIELD)
                        .append(field)
                        .append(" = :")
                        .append(field)
                        .append(" LIMIT 1")
        );
    }

    public static String selectOneByMultipleField(List<String> fields) {
        return String.valueOf(
                new StringBuilder(SELECT_BY_ONE_FIELD)
                        .append(String.join(" and ",
                                fields.stream().map(e -> new StringBuilder(e).append(" = :").append(e))
                                        .collect(Collectors.toList())
                                ))
                        .append(" LIMIT 1")
        );
    }

    public static String insertOne(List<String> fields) {
        return String.valueOf(new StringBuilder("INSERT INTO ")
                .append(TABLE_NAME)
                .append(" ( ")
                .append(String.join(", ", fields))
                .append(" ) VALUES (:")
                .append(String.join(", :", fields))
                .append(")")
        );
    }

    public static String updateOne(List<String> setFields, List<String> whereFields) {
        return String.valueOf(new StringBuilder("UPDATE ")
                .append(TABLE_NAME)
                .append(" SET ")
                .append(String.join(", ",
                        setFields.stream()
                                .map(e -> new StringBuilder(e).append(" = :").append(e))
                                .collect(Collectors.toList())))
                .append(" WHERE ")
                .append(String.join(" and ",
                        whereFields.stream()
                                .map(e -> new StringBuilder(e).append(" = :").append(e))
                                .collect(Collectors.toList())))
        );
    }
}
