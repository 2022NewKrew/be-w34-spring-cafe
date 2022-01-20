package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.persistence.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReplyRepositoryImpl implements ReplyRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Reply> rowMapper;

    @Override
    public void save(Reply reply, Long articleId) {
        String sql = "INSERT INTO REPLIES(writer_id, article_id, contents) VALUES(?, ?, ?)";
        writeRecord(sql,
                reply.getWriter().getId(),
                articleId,
                reply.getContent()
        );
    }

    @Override
    public List<Reply> findByArticleId(Long articleId) {
        String sql = "SELECT * FROM REPLIES WHERE article_id = ? AND deleted = FALSE";
        return readRecords(sql, articleId);
    }

    @Override
    public Optional<Reply> findById(Long id) {
        String sql = "SELECT * FROM REPLIES WHERE id = ? AND deleted = FALSE";
        return readRecord(sql, id);
    }

    @Override
    public void deleteByArticleId(Long id) {
        String sql = "UPDATE REPLIES SET deleted = TRUE WHERE id = ?";
        writeRecord(sql, id);
    }

    private Optional<Reply> readRecord(String sql, Object... params) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, params));
        } catch(EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private List<Reply> readRecords(String sql, Object... params) {
        return jdbcTemplate.query(sql, rowMapper, params);
    }

    private void writeRecord(String sql, Object... params) {
        jdbcTemplate.update(sql, params);
    }
}
