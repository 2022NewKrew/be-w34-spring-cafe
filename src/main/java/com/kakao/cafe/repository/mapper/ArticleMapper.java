package com.kakao.cafe.repository.mapper;

import com.kakao.cafe.domain.article.Article;
import org.springframework.jdbc.core.RowMapper;

import java.io.BufferedReader;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class ArticleMapper implements RowMapper<Article> {

    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String author = rs.getString("author");
        String title = rs.getString("title");
        String content = clobToString(rs.getClob("content"));
        LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = rs.getTimestamp("updated_at").toLocalDateTime();
        Article article = new Article(author, title, content, createdAt, updatedAt);
        article.setId(id);
        return article;
    }

    private String clobToString(Clob clob) throws SQLException {
        BufferedReader reader = new BufferedReader(clob.getCharacterStream());
        return reader.lines().collect(Collectors.joining("\n"));
    }

}
