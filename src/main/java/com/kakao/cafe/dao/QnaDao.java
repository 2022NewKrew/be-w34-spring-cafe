package com.kakao.cafe.dao;

import com.kakao.cafe.domain.Qna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QnaDao {
    private JdbcTemplate jdbcTemplate;

    public QnaDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void save(Qna qna) {
        final String sql = "INSERT INTO QNA (TITLE, CONTENT) VALUES (?, ?)";
        jdbcTemplate.update(sql, qna.getTitle(), qna.getContent());
    }

    public List<Qna> findAll() {
        final String sql = "SELECT qnaId, title, content, views, createDate FROM QNA ORDER BY qnaId DESC";

        return jdbcTemplate.query(sql, new QnaRowMapper());
    }

    public Qna findByQnaId(long qnaId) {
        final String sql = "SELECT qnaId, title, content, views, createDate FROM QNA WHERE qnaId = ?";

        final Qna qna = jdbcTemplate.queryForObject(sql, new QnaRowMapper(), new Object[] { qnaId });
        return qna;
    }

    public void updateViews(long qnaId) {
        final String sql = "UPDATE QNA SET VIEWS = VIEWS + 1 WHERE QNAID = ?";
        jdbcTemplate.update(sql, qnaId);
    }

    private class QnaRowMapper implements RowMapper<Qna> {
        @Override
        public Qna mapRow(ResultSet rs, int rowNum) throws SQLException {
            Qna qna = new Qna();

            qna.setQnaId(rs.getLong("qnaId"));
            qna.setTitle(rs.getString("title"));
            qna.setContent(rs.getString("content"));
            qna.setViews(rs.getInt("views"));
            qna.setCreateDate(rs.getTimestamp("createDate").toLocalDateTime());

            return qna;
        }
    }
}
