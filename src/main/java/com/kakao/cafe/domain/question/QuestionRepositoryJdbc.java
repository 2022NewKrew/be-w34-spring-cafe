package com.kakao.cafe.domain.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("QuestionRepositoryJdbc")
public class QuestionRepositoryJdbc implements QuestionRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Question question) {
        String sql = "INSERT INTO QUESTION(TITLE, WRITER, CONTENTS) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
                question.getTitle(), question.getWriter(),question.getContents());
    }

    @Override
    public Question findById(int id) {
        String sql = "SELECT ID, TITLE, WRITER, CONTENTS, CREATED_AT FROM QUESTION WHERE ID = ?";
        return this.jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> Question.builder()
                        .id(rs.getInt("ID"))
                        .title(rs.getString("TITLE"))
                        .writer(rs.getString("WRITER"))
                        .contents(rs.getString("CONTENTS"))
                        .createdAt(rs.getTimestamp("CREATED_AT").toLocalDateTime())
                        .build(),
                id);
    }

    @Override
    public List<Question> findAll() {
        String sql = "SELECT ID, TITLE, WRITER, CONTENTS, CREATED_AT FROM QUESTION";
        return this.jdbcTemplate.query(sql,
                (rs, rowNum) -> Question.builder()
                        .id(rs.getInt("ID"))
                        .title(rs.getString("TITLE"))
                        .writer(rs.getString("WRITER"))
                        .contents(rs.getString("CONTENTS"))
                        .createdAt(rs.getTimestamp("CREATED_AT").toLocalDateTime())
                        .build()
                );
    }
}
