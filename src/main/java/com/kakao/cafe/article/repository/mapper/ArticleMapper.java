package com.kakao.cafe.article.repository.mapper;

import com.kakao.cafe.article.domain.Article;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleMapper implements RowMapper<Article> {

    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        Article article = Article.builder()
                .id(rs.getLong("id"))
                .title(rs.getString("title"))
                .authorId(rs.getLong("author"))
                .date(rs.getTimestamp("write_date").toLocalDateTime())
                .hits(rs.getInt("hits"))
                .contents(rs.getString("content"))
                .build();
        return article;
    }
}
