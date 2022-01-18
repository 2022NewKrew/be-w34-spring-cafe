package com.kakao.cafe.domain.question.repositoryimpl;

import com.kakao.cafe.domain.question.Question;
import com.kakao.cafe.domain.question.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("QuestionRepositoryJdbc")
public class QuestionRepositoryJdbc implements QuestionRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Question question) {
        if(question.isNew()){
            insert(question);
            return;
        }
        update(question);
    }

    @Override
    public Question findById(int id) {
        String sql = "SELECT ID, USER_ID, TITLE, WRITER, CONTENTS, CREATED_AT FROM `QUESTION` WHERE ID = ?";
        try{
            return this.jdbcTemplate.queryForObject(sql,
                    (rs, rowNum) -> Question.builder()
                            .id(rs.getInt("ID"))
                            .userId(rs.getInt("USER_ID"))
                            .title(rs.getString("TITLE"))
                            .writer(rs.getString("WRITER"))
                            .contents(rs.getString("CONTENTS"))
                            .createdAt(rs.getTimestamp("CREATED_AT").toLocalDateTime())
                            .build(),
                    id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Question> findAll() {
        String sql = "SELECT ID, USER_ID, TITLE, WRITER, CONTENTS, CREATED_AT FROM `QUESTION`";
        return this.jdbcTemplate.query(sql,
                (rs, rowNum) -> Question.builder()
                        .id(rs.getInt("ID"))
                        .userId(rs.getInt("USER_ID"))
                        .title(rs.getString("TITLE"))
                        .writer(rs.getString("WRITER"))
                        .contents(rs.getString("CONTENTS"))
                        .createdAt(rs.getTimestamp("CREATED_AT").toLocalDateTime())
                        .build()
                );
    }

    private void insert(Question question){
        String sql = "INSERT INTO `QUESTION`(USER_ID, TITLE, WRITER, CONTENTS) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                question.getUserId(), question.getTitle(), question.getWriter(),question.getContents());
    }

    private void update(Question question){
        String sql = "UPDATE `QUESTION` SET TITLE=?, WRITER=?, CONTENTS=? WHERE ID=?";
        jdbcTemplate.update(sql,
                question.getTitle(), question.getWriter(),question.getContents(), question.getId());
    }
}
