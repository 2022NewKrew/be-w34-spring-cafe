package com.kakao.cafe.question.mapper;

import com.kakao.cafe.question.Question;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class QuestionRowMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {

        Question question = new Question();
        question.setWriter(rs.getString("writer"));
        question.setTitle(rs.getString("title"));
        question.setContents(rs.getString("contents"));
        question.setId(rs.getLong("id"));
        question.setMemberId(rs.getLong("member_id"));
        question.setCreateTime((LocalDateTime) rs.getTimestamp("create_time").toLocalDateTime());

        return question;
    }
}
