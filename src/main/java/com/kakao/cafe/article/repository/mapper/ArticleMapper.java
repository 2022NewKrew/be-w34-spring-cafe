package com.kakao.cafe.article.repository.mapper;

import com.kakao.cafe.article.domain.Article;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleMapper implements RowMapper<Article> {

    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        Article article = new Article(rs.getLong("id"),
                rs.getString("title"),
                rs.getLong("author"),
                rs.getTimestamp("write_date").toLocalDateTime(),
                rs.getInt("hits"),
                rs.getString("content"));

        return article;
    }
}
