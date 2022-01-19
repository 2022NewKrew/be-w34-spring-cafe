package com.kakao.cafe.domain.reply;

import com.kakao.cafe.core.DBConst;
import com.kakao.cafe.domain.reply.dto.ReplyCreateForm;
import com.kakao.cafe.domain.reply.dto.ReplyResponse;
import com.kakao.cafe.domain.reply.dto.ReplyUpdateForm;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ReplyJdbcRepository {
    private final JdbcTemplate jdbcTemplate;

    public ReplyJdbcRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void save(ReplyCreateForm form) {
        String sql = "insert into " + DBConst.REPLY_DB + " (articleId, replyerId, content, createdAt, isDeleted) values(?,?,?,?,?)";
        jdbcTemplate.update(sql, form.getArticleId(), form.getReplyerId(), form.getContent(), LocalDateTime.now(), 'N');
    }

    public List<ReplyResponse> getAllByArticleId(Long articleId) {
        String sql = "select r.id as id, u.name as name, content, createdAt from reply as r, users as u where articleId=? and r.replyerId = u.id and isDeleted = 'N'";
        return jdbcTemplate.query(sql, replyRowMapper(), articleId);
    }

    public void update(Long id, String updateContent) {
        jdbcTemplate.update("update reply set content = ? where id = ?", updateContent, id);
    }

    public void delete(Long id) {
        jdbcTemplate.update("update reply set isDeleted = 'Y' where id = ?", id);
    }

    private RowMapper<ReplyResponse> replyRowMapper() {
        return (rs, rowNum) -> {
            ReplyResponse reply = new ReplyResponse();
            reply.setId(rs.getLong("id"));
            reply.setContent(rs.getString("content"));
            reply.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
            reply.setReplyerName(rs.getString("name"));
            return reply;
        };
    }

    public boolean checkAuthor(Long id, Long userId) {
        List<Integer> query = jdbcTemplate.query("select id from reply where id = ? and replyerId = ?", isExistMapper(), id, userId);
        return !query.isEmpty();
    }

    private RowMapper<Integer> isExistMapper() {
        return (rs, rowNum) -> rs.getInt("id");
    }

    public Optional<ReplyUpdateForm> getContentFindById(Long replyId) {
        List<ReplyUpdateForm> query = jdbcTemplate.query("select id, content, articleId from reply where id = ?", updateFormMapper(), replyId);
        return query.stream().findAny();
    }

    private RowMapper<ReplyUpdateForm> updateFormMapper() {
        return (rs, rowNum) -> {
            ReplyUpdateForm updateForm = new ReplyUpdateForm();
            updateForm.setId(rs.getLong("id"));
            updateForm.setArticleId(rs.getLong("articleId"));
            updateForm.setContent(rs.getString("content"));
            return updateForm;
        };
    }
}
