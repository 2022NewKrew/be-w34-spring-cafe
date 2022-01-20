package com.kakao.cafe.web;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ReplyRepository {
    private static final String INSERT_VALUES = "INSERT INTO REPLY(QUESTION_ID, WRITER_ID, CONTENTS) VALUES(?, ?, ?)";
    private static final String SELECT_BY_QUESTION_ID = "SELECT REPLY.*, USERS.NAME AS WRITER_NAME FROM REPLY, USERS " +
            "WHERE QUESTION_ID = ? AND WRITER_ID = USERS.ID";

    private static final String SELECT_BY_ID = "SELECT * FROM REPLY WHERE ID = ?";
    private static final String DELETE_BY_ID = "DELETE FROM REPLY WHERE ID = ?";
    private final JdbcTemplate jdbcTemplate;

    public ReplyRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    // 전체 답글 목록 조회
    public List<Reply> getReplyList(int questionId) throws DataAccessException {
        return jdbcTemplate.query(
                SELECT_BY_QUESTION_ID,
                new BeanPropertyRowMapper<>(Reply.class),
                questionId
        );
    }

    // 답글 고유 id를 이용해 한개의 답글 조회
    public Reply getReply(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(
                SELECT_BY_ID,
                new BeanPropertyRowMapper<>(Reply.class),
                id
        );
    }

    // 답글 추가
    public void addReply(int questionId, String writerId, String contents) throws DataAccessException {
        // 고유 id와 타임스탬프는 DB에 디폴트 값으로 생성됩니다.
        jdbcTemplate.update(INSERT_VALUES, questionId, writerId, contents);
    }
}
