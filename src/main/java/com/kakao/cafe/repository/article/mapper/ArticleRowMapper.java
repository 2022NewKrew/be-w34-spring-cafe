package com.kakao.cafe.repository.article.mapper;

import com.kakao.cafe.domain.Article;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleRowMapper implements RowMapper<Article> {
    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Article.builder()
                .id(rs.getLong("id"))
                .writer(rs.getString("writer"))
                .title(rs.getString("title"))
                .contents(rs.getString("contents"))
                .build();
    }
}
