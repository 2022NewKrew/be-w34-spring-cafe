package com.kakao.cafe.reply.repository;

import com.kakao.cafe.reply.entity.ReplyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyRepository {
    public static final String INSERT_REPLY = "INSERT INTO TB_REPLY(article_id, writer, comment) values (?, ?, ?)";
    public static final String SELECT_REPLY_BY_ARTICLE_ID = "SELECT * FROM TB_REPLY WHERE article_id = ? AND DELETED = FALSE";
    public static final String DELETE_REPLY_SOFT = "UPDATE TB_REPLY SET DELETED = TRUE WHERE ID = ? AND ARTICLE_ID = ?";
    public static final String SELECT_REPLY_BY_ID_WRITER = "SELECT * FROM TB_REPLY WHERE ID = ? AND WRITER = ?";

    private final JdbcTemplate jdbcTemplate;

    public void save(ReplyEntity replyEntity) {
        jdbcTemplate.update(INSERT_REPLY, replyEntity.getArticleId(), replyEntity.getWriter(), replyEntity.getComment());
    }

    public List<ReplyEntity> findAllByArticleId(Long articleId) {
        return jdbcTemplate.query(SELECT_REPLY_BY_ARTICLE_ID, this::convertReplyEntity, articleId);
    }

    public void delete(Long articleId, Long replyId) {
        jdbcTemplate.update(DELETE_REPLY_SOFT, replyId, articleId);
    }

    public List<ReplyEntity> findByIdAndWriter(Long replyId, String userName) {
        return jdbcTemplate.query(SELECT_REPLY_BY_ID_WRITER, this::convertReplyEntity, replyId, userName);
    }

    private ReplyEntity convertReplyEntity(ResultSet rs, int rowNum) throws SQLException {
        return ReplyEntity.builder()
                .id(rs.getLong("id"))
                .articleId(rs.getLong("article_id"))
                .writer(rs.getString("writer"))
                .comment(rs.getString("comment"))
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                .build();
    }
}
