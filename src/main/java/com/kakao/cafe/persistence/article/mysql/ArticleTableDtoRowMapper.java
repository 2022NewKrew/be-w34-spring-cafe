package com.kakao.cafe.persistence.article.mysql;

import com.kakao.cafe.persistence.article.mysql.dto.ArticleTableDto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ArticleTableDtoRowMapper implements RowMapper<ArticleTableDto> {

    @Override
    public ArticleTableDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ArticleTableDto.builder()
                .articleId(Integer.valueOf(rs.getString("article_id")))
                .userId(String.valueOf(rs.getString("user_id")))
                .articleCreatedAt(rs.getTimestamp("article_created_at").toLocalDateTime())
                .articleTitle(rs.getString("article_title"))
                .articleContents(rs.getString("article_contents"))
                .build();
    }

}
