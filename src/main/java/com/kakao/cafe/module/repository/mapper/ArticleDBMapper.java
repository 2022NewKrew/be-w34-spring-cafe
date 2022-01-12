package com.kakao.cafe.module.repository.mapper;

import com.kakao.cafe.module.model.domain.Article;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ArticleDBMapper implements RowMapper<Article> {

    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        Long author_id = rs.getLong("author_id");
        String title = rs.getString("title");
        String contents = rs.getString("contents");
        LocalDateTime created = rs.getTimestamp("created").toLocalDateTime();
        Integer viewCount = rs.getInt("view_count");
        Integer commentCount = rs.getInt("comment_count");
        return new Article(id, author_id, title, contents, created, viewCount, commentCount);
    }
}
