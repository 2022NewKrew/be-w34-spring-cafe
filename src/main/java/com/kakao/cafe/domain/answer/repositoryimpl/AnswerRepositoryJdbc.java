package com.kakao.cafe.domain.answer.repositoryimpl;

import com.kakao.cafe.domain.answer.Answer;
import com.kakao.cafe.domain.answer.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AnswerRepositoryJdbc implements AnswerRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Answer save(Answer answer) {
        if(answer.isNew()){
            insert(answer);
            return answer;
        }
        update(answer);
        return answer;
    }

    @Override
    public Answer findById(int id) {
        String sql = "SELECT A.ID, A.USER_ID, A.QUESTION_ID, U.NAME as WRITER, A.CONTENTS, A.CREATED_AT FROM `ANSWER` A"
                + " INNER JOIN `USER` U ON U.ID = A.USER_ID"
                + " WHERE A.IS_DELETED=FALSE AND A.ID=?";
        try{
            return this.jdbcTemplate.queryForObject(sql,
                    (rs, rowNum) -> Answer.builder()
                            .id(rs.getInt("ID"))
                            .userId(rs.getInt("USER_ID"))
                            .questionId(rs.getInt("QUESTION_ID"))
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
    public void deleteById(int id) {
        String sql = "UPDATE `ANSWER` SET IS_DELETED=? WHERE ID=?";
        jdbcTemplate.update(sql, true, id);
    }

    private void insert(Answer answer){
        String sql = "INSERT INTO `ANSWER`(USER_ID, QUESTION_ID, CONTENTS) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
                answer.getUserId(), answer.getQuestionId(), answer.getContents());
    }

    private void update(Answer answer){
        String sql = "UPDATE `QUESTION` SET CONTENTS=? WHERE ID=?";
        jdbcTemplate.update(sql,
                answer.getContents(), answer.getId());
    }
}
