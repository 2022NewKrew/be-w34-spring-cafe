package com.kakao.cafe.persistence.article.h2;

import java.util.List;

public class H2ArticleQueryBuilder {
    private static final String TABLE_NAME = "ARTICLE";
    private static final List<String> FIELDS = List.of("article_id", "article_writer", "article_created_at", "article_title", "article_contents");
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

    private H2ArticleQueryBuilder() {}

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

}
