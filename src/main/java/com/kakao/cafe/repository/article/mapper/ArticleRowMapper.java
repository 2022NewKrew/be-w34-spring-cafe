package com.kakao.cafe.repository.article.mapper;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ArticleRowMapper implements RowMapper<Article> {
    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = User.builder()
                .userId(rs.getString("writer_id"))
                .userName(rs.getString("writer_name"))
                .build();
        return Article.builder()
                .id(rs.getLong("id"))
                .writer(user)
                .title(rs.getString("title"))
                .contents(rs.getString("contents"))
                .build();
    }
}
