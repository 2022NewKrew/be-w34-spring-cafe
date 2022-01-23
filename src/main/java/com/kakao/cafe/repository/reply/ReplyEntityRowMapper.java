package com.kakao.cafe.repository.reply;

import com.kakao.cafe.entity.ReplyEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ReplyEntityRowMapper implements RowMapper<ReplyEntity> {
    @Override
    public ReplyEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ReplyEntity(rs.getLong("id"), rs.getString("content"),
                rs.getString("writer"), rs.getTimestamp("write_date").toLocalDateTime(),
                rs.getLong("user_id"), rs.getLong("article_id"));
    }
}
