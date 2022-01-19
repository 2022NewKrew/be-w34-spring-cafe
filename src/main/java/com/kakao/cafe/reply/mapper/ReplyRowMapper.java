package com.kakao.cafe.reply.mapper;

import com.kakao.cafe.reply.Reply;
import com.kakao.cafe.reply.ReplyStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ReplyRowMapper implements RowMapper<Reply> {
    @Override
    public Reply mapRow(ResultSet rs, int rowNum) throws SQLException {

        Reply reply = new Reply();

        reply.setId(rs.getLong("id"));
        reply.setComment(rs.getString("comment"));
        reply.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
        reply.setWriter(rs.getString("writer"));
        reply.setMemberId(rs.getLong("member_id"));
        reply.setQuestionId(rs.getLong("question_id"));
        reply.setStatus(ReplyStatus.valueOf(rs.getString("status")));

        return reply;
    }
}
