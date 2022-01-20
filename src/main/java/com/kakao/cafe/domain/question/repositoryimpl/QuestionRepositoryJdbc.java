package com.kakao.cafe.domain.question.repositoryimpl;

import com.kakao.cafe.domain.answer.Answer;
import com.kakao.cafe.domain.question.Question;
import com.kakao.cafe.domain.question.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@RequiredArgsConstructor
@Repository("QuestionRepositoryJdbc")
public class QuestionRepositoryJdbc implements QuestionRepository {
    private final JdbcTemplate jdbcTemplate;

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
        String sql = "SELECT Q.ID, Q.USER_ID, Q.TITLE, U.NAME as WRITER, Q.CONTENTS, Q.CREATED_AT FROM `QUESTION` Q INNER JOIN `USER` U"
                + " ON U.ID = Q.USER_ID"
                + " WHERE U.ID = ? and IS_DELETED=FALSE";
        try{
            return this.jdbcTemplate.queryForObject(sql,
                    (rs, rowNum) -> Question.builder()
                            .id(rs.getInt("ID"))
                            .userId(rs.getInt("USER_ID"))
                            .answers(findAnswersByQuestionId(id))
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
        String sql = "SELECT Q.ID, Q.USER_ID, Q.TITLE, U.NAME as WRITER, Q.CONTENTS, Q.CREATED_AT FROM `QUESTION` Q INNER JOIN `USER` U"
                + " ON U.ID = Q.USER_ID"
                + " WHERE Q.IS_DELETED=FALSE";
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

    @Override
    public void deleteById(int id){
        String sql = "UPDATE `QUESTION` SET IS_DELETED=? WHERE ID=?";
        jdbcTemplate.update(sql, true, id);
    }

    private void insert(Question question){
        String sql = "INSERT INTO `QUESTION`(USER_ID, TITLE, CONTENTS) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
                question.getUserId(), question.getTitle(), question.getContents());
    }

    private void update(Question question){
        String sql = "UPDATE `QUESTION` SET TITLE=?, CONTENTS=? WHERE ID=?";
        jdbcTemplate.update(sql,
                question.getTitle(), question.getContents(), question.getId());
    }

    private List<Answer> findAnswersByQuestionId(int questionId){
        String sql = "SELECT A.ID, A.USER_ID, A.QUESTION_ID, U.NAME as WRITER, A.CONTENTS, A.CREATED_AT FROM `ANSWER` A"
                + " INNER JOIN `USER` U ON U.ID = A.USER_ID"
                + " INNER JOIN `QUESTION` Q ON Q.ID = A.QUESTION_ID"
                + " WHERE A.IS_DELETED=FALSE AND A.QUESTION_ID = ?";
        return this.jdbcTemplate.query(sql,
                (rs, rowNum) -> Answer.builder()
                        .id(rs.getInt("ID"))
                        .userId(rs.getInt("USER_ID"))
                        .questionId(rs.getInt("QUESTION_ID"))
                        .writer(rs.getString("WRITER"))
                        .contents(rs.getString("CONTENTS"))
                        .createdAt(rs.getTimestamp("CREATED_AT").toLocalDateTime())
                        .build(),
                questionId
        );
    }
}
