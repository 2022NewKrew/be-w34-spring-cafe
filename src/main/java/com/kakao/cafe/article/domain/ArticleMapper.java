package com.kakao.cafe.article.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper implements RowMapper<Article> {

    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Article.builder()
            .id(rs.getLong("article_id"))
            .title(rs.getString("title"))
            .body(rs.getString("body"))
            .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
            .viewCount(rs.getInt("view_count"))
            .authorId(rs.getLong("author_id"))
            .build();
    }
}
