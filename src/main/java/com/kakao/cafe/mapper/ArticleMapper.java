package com.kakao.cafe.mapper;

import com.kakao.cafe.domain.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class ArticleMapper implements RowMapper<Article> {

    private final UserMapper userMapper;

    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Article.builder()
                .id(rs.getLong("article_seq_id"))
                .writer(userMapper.mapRow(rs, rowNum))
                .title(rs.getString("title"))
                .contents(rs.getString("contents"))
                .build();

    }
}
