package com.kakao.cafe.web;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionRepository {
    private static final String INSERT_VALUES = "INSERT INTO QUESTION(WRITER_ID, TITLE, CONTENTS) VALUES (?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT QUESTION.*, USERS.NAME AS WRITER_NAME FROM QUESTION, USERS " +
            "WHERE QUESTION.ID = ? AND QUESTION.WRITER_ID = USERS.ID";

    private static final String SELECT_ALL = "SELECT QUESTION.*, USERS.NAME AS WRITER_NAME FROM QUESTION, USERS " +
            "WHERE QUESTION.WRITER_ID = USERS.ID";

    private static final String UPDATE_VALUES = "UPDATE QUESTION SET TITLE = ?, CONTENTS = ? WHERE ID = ?";
    private static final String DELETE_BY_ID = "DELETE FROM QUESTION WHERE ID = ?";
    private final JdbcTemplate jdbcTemplate;

    public QuestionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 전체 게시글 목록 조회
    public List<Question> getQuestionList() throws DataAccessException {
        return jdbcTemplate.query(
                SELECT_ALL,
                new BeanPropertyRowMapper<>(Question.class)
        );
    }

    // 게시글 고유 id를 이용해 한개의 게시글 조회
    public Question getQuestion(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(
                SELECT_BY_ID,
                new BeanPropertyRowMapper<>(Question.class),
                id
        );
    }

    // 게시글 추가
    public void addQuestion(String writerId, String title, String contents) throws DataAccessException {
        // 고유 id와 타임스탬프는 DB에 디폴트 값으로 생성됩니다.
        jdbcTemplate.update(INSERT_VALUES, writerId, title, contents);
    }

    // 게시글 수정
    public void updateQuestion(int id, String title, String contents) throws DataAccessException {
        jdbcTemplate.update(UPDATE_VALUES, title, contents, id);
    }

    // 게시글 삭제
    public void deleteQuestion(int id) throws DataAccessException {
        jdbcTemplate.update(DELETE_BY_ID, id);
    }
}
