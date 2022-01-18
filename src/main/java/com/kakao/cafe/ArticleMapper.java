package com.kakao.cafe;

import com.kakao.cafe.domain.Article;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleMapper implements RowMapper<Article> {
    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        Article article = new Article();

        article.setId(rs.getInt("id"));
        article.setAuthorId(rs.getInt("author_id"));
        article.setCreatedAt(new java.sql.Timestamp(rs.getTimestamp("createdate").getTime()).toLocalDateTime());
        article.setTitle(rs.getString("title"));
        article.setContent(rs.getString("content"));

        return article;
    }
}
