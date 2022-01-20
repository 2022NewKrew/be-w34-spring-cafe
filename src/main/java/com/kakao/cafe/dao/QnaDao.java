package com.kakao.cafe.dao;

import com.kakao.cafe.domain.Qna;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class QnaDao {
    private JdbcTemplate jdbcTemplate;

    public QnaDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void save(Qna qna) {
        final String sql = "INSERT INTO QNA (title, content, author) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, qna.getTitle(), qna.getContent(), qna.getAuthor());
    }

    public List<Qna> findAll() {
        final String sql = "SELECT qnaId, title, content, views, author, createDate FROM QNA WHERE isDeleted = FALSE ORDER BY qnaId DESC";

        return jdbcTemplate.query(sql, new QnaRowMapper());
    }

    public Qna findByQnaId(long qnaId) {
        final String sql = "SELECT qnaId, title, content, views, author, createDate FROM QNA WHERE isDeleted = FALSE AND qnaId = ?";
        return jdbcTemplate.queryForObject(sql, new QnaRowMapper(), new Object[] { qnaId });
    }

    public void updateViews(long qnaId) {
        final String sql = "UPDATE QNA SET views = views + 1 WHERE qnaId = ?";
        jdbcTemplate.update(sql, qnaId);
    }

    public void update(Qna qna) {
        final String sql = "UPDATE QNA SET title = ?, content = ? WHERE qnaId = ?";
        jdbcTemplate.update(sql, qna.getTitle(), qna.getContent(), qna.getQnaId());
    }

    public void deleteByQnaId(long qnaId) {
        final String sql = "UPDATE QNA SET isDeleted = TRUE WHERE qnaId = ?";
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
            qna.setAuthor(rs.getString("author"));
            qna.setCreateDate(rs.getTimestamp("createDate").toLocalDateTime());

            return qna;
        }
    }
}
