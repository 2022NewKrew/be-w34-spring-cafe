package com.kakao.cafe.article.entity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kakao.cafe.article.entity.ArticleReply;

@Component
public class ArticleReplyRowMapper implements RowMapper<ArticleReply> {
    @Override
    public ArticleReply mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ArticleReply.builder()
                           .id(rs.getInt(("id")))
                           .articleId(rs.getInt("article_id"))
                           .writer(rs.getString("writer"))
                           .contents(rs.getString("contents"))
                           .createTime(rs.getTimestamp("create_time").toLocalDateTime())
                           .build();
    }
}
