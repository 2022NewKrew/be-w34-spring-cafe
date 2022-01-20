package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Question;
import com.kakao.cafe.dto.QuestionListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcTemplateQuestionRepository implements QuestionRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Question save(Question question) {
        final String sql = "insert into `question` (`writer`, `title`, `contents`, `created_date_time`) values(?,?,?,?)";
        Object[] parameters = {
                question.getWriter(),
                question.getTitle(),
                question.getContents(),
                Timestamp.valueOf(question.getCreatedDateTime()),
        };
        jdbcTemplate.update(sql, parameters);
        return null;
    }

    @Override
    public Optional<Question> findById(Long id) {
        List<Question> result = jdbcTemplate.query("select * from `question` where id = ?", questionRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public List<Question> findAll() {
        return jdbcTemplate.query("select * from `question`", questionRowMapper());
    }

    @Override
    public List<QuestionListResponse> findAllAndWriterNickname(){
        final String sql = "select " +
                "q.id as questionId, q.writer as userId, q.title as title, q.contents as contents, q.created_date_time as created_date_time, u.nickname as writer " +
                "from `question` as q join `user` as u " +
                "on q.writer = u.id";
        return jdbcTemplate.query(sql, questionListResponseRowMapper());
    }

    private RowMapper<QuestionListResponse> questionListResponseRowMapper(){
        return (rs, rowNum) -> QuestionListResponse.builder()
                .questionId(rs.getLong("questionId"))
                .title(rs.getString("title"))
                .createdDateTime(rs.getTimestamp("created_date_time").toLocalDateTime().format(DateTimeFormatter.ofPattern("YY-MM-DD HH:mm")))
                .writer(rs.getString("writer"))
                .userId(rs.getLong("userId"))
                .numberOfReply(99)
                .build();
    }

    private RowMapper<Question> questionRowMapper(){
        return (rs, rowNum) -> Question.builder()
                .id(rs.getLong("id"))
                .writer(rs.getLong("writer"))
                .title(rs.getString("title"))
                .contents(rs.getString("contents"))
                .createdDateTime(rs.getTimestamp("created_date_time").toLocalDateTime())
                .build();

    }
}
