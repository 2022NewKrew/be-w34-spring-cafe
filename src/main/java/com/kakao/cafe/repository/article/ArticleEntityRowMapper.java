package com.kakao.cafe.repository.article;

import com.kakao.cafe.entity.ArticleEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ArticleEntityRowMapper implements RowMapper<ArticleEntity> {
    @Override
    public ArticleEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ArticleEntity(rs.getLong("id"), rs.getString("title"),
                rs.getString("content"), rs.getString("writer"),
                rs.getTimestamp("write_date").toLocalDateTime(), rs.getInt("views"),
                rs.getLong("user_id"));
    }
}
