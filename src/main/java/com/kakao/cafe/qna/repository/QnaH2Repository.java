package com.kakao.cafe.qna.repository;

import com.kakao.cafe.exception.CustomEmptyDataAccessException;
import com.kakao.cafe.qna.domain.Qna;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class QnaH2Repository implements QnaRepository {

    private final RowMapper<Qna> qnaMapper = getQnaMapper();
    private final JdbcTemplate jdbcTemplate;

    public QnaH2Repository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Qna qna) {
        String sql = "INSERT INTO qnas (writer, title, contents, create_time) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(
            sql,
            qna.getWriter(),
            qna.getTitle(),
            qna.getContents(),
            qna.getCreateTime()
        );
    }

    public List<Qna> findAll() {
        String sql = "SELECT * FROM qnas";
        return jdbcTemplate.query(sql, qnaMapper);
    }

    public Qna findById(long id) {
        String sql = "SELECT * FROM qnas WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, qnaMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new CustomEmptyDataAccessException(e);
        }
    }

    @Override
    public Qna findByIdAndWriter(long id, String userId) {
        String sql = "SELECT * FROM qnas WHERE id = ? AND writer = ?";
        try {
            return jdbcTemplate.queryForObject(sql, qnaMapper, id, userId);
        } catch (EmptyResultDataAccessException e) {
            throw new CustomEmptyDataAccessException(e);
        }
    }

    @Override
    public void update(Qna qna) {
        String sql = "UPDATE qnas SET title = ?, contents = ? WHERE id = ? AND writer = ?";
        jdbcTemplate.update(
            sql,
            qna.getTitle(),
            qna.getContents(),
            qna.getId(),
            qna.getWriter()
        );
    }

    @Override
    public void delete(long id, String userId) {
        String sql = "DELETE FROM qnas WHERE id = ? AND writer = ?";
        jdbcTemplate.update(
            sql,
            id,
            userId
        );
    }

    public RowMapper<Qna> getQnaMapper() {
        return (resultSet, rowNumber) -> new Qna(
            resultSet.getLong(1),
            resultSet.getString(2),
            resultSet.getString(3),
            resultSet.getString(4)
        );
    }
}
