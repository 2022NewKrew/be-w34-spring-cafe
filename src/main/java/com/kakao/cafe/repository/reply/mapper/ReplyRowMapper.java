package com.kakao.cafe.repository.reply.mapper;

import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Component
public class ReplyRowMapper implements RowMapper<Reply> {
    @Override
    public Reply mapRow(ResultSet rs, int rowNum) throws SQLException {
        User writer = User.builder().userId(rs.getString("writer_id"))
                .userName(rs.getString("writer_name"))
                .build();
        Timestamp updatedTime = rs.getTimestamp("updated_time");
        return Reply.builder()
                .id(rs.getLong("id"))
                .articleId(rs.getLong("article_id"))
                .writer(writer)
                .comment(rs.getString("comment"))
                .createdTime(rs.getTimestamp("created_time").toLocalDateTime())
                .updatedTime(updatedTime != null ? updatedTime.toLocalDateTime() : null)
                .build();
    }
}
