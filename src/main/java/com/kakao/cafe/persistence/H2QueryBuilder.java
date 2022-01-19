package com.kakao.cafe.persistence;

import java.util.List;
import java.util.stream.Collectors;

public class H2QueryBuilder {
    private final String table;
    private final List<String> fields;

    public H2QueryBuilder(String table, List<String> fields) {
        this.table = table;
        this.fields = fields;
    }
    
    public String selectAll() {
        return String.valueOf(new StringBuilder("SELECT ")
                .append(String.join(", ", fields))
                .append(" FROM ")
                .append(table));
    }

    public String selectOneByField(String field) {
        return String.valueOf(
                new StringBuilder(selectAll())
                        .append(" WHERE ")
                        .append(field)
                        .append(" = :")
                        .append(field)
                        .append(" LIMIT 1")
        );
    }

    public String selectOneByMultipleField(List<String> fields) {
        return String.valueOf(
                new StringBuilder(selectAll())
                        .append(" WHERE ")
                        .append(String.join(" and ",
                                fields.stream().map(e -> new StringBuilder(e).append(" = :").append(e))
                                        .collect(Collectors.toList())
                        ))
                        .append(" LIMIT 1")
        );
    }

    public String insertOne(List<String> fields) {
        return String.valueOf(new StringBuilder("INSERT INTO ")
                .append(table)
                .append(" ( ")
                .append(String.join(", ", fields))
                .append(" ) VALUES (:")
                .append(String.join(", :", fields))
                .append(")")
        );
    }

    public String updateOne(List<String> setFields, List<String> whereFields) {
        return String.valueOf(new StringBuilder("UPDATE ")
                .append(table)
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
