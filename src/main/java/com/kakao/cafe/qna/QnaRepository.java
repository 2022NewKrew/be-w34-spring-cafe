package com.kakao.cafe.qna;

import com.kakao.cafe.qna.domain.Qna;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class QnaRepository {

    private final RowMapper<Qna> qnaMapper = getQnaMapper();
    private final JdbcTemplate jdbcTemplate;

    public QnaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Qna qna) {
        String sql = "INSERT INTO qnas (writer, title, contents, create_time) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
            qna.getWriter(),
            qna.getTitle(),
            qna.getContents(),
            qna.getCreateTime());
    }

    public List<Qna> findAll() {
        String sql = "SELECT * FROM qnas";
        return jdbcTemplate.query(sql, qnaMapper);
    }

    public Qna findById(long id) {
        String sql = "SELECT * FROM qnas WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, qnaMapper, id);
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
