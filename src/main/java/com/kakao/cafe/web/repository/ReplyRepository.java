package com.kakao.cafe.web.repository;

import com.kakao.cafe.domain.Reply;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReplyRepository {
    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;

    public ReplyRepository(JdbcTemplate jdbcTemplate, UserRepository userRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
    }

    public void insertReply(Reply reply) {
        jdbcTemplate.update(QueryConstants.REPLY_INSERT, reply.getAuthor().getId(), reply.getArticleId(), reply.getContent());
    }

    public List<Reply> selectReplies(int articleId) {
        return jdbcTemplate.query(QueryConstants.REPLY_SELECT_BY_ARTICLEID,
                (rs, rowNum) -> new Reply(
                        rs.getInt("id"),
                        userRepository.selectByUserId(rs.getInt("author_id")),
                        rs.getInt("article_id"),
                        new java.sql.Timestamp(rs.getTimestamp("createdate").getTime()).toLocalDateTime(),
                        rs.getString("content")
                ), articleId);
    }

    public void deleteReply(int id) {
        jdbcTemplate.update(QueryConstants.REPLY_DELETE, id);
    }

    public void updateReply(int id, Reply updatedReply) {
        jdbcTemplate.update(QueryConstants.REPLY_UPDATE, updatedReply.getContent(), id);
    }

    public Reply selectByReplyId(int id) {
        return jdbcTemplate.queryForObject(QueryConstants.REPLY_SELECT_BY_ID,
                (rs, rowNum) -> new Reply(
                        rs.getInt("id"),
                        userRepository.selectByUserId(rs.getInt("author_id")),
                        rs.getInt("article_id"),
                        new java.sql.Timestamp(rs.getTimestamp("createdate").getTime()).toLocalDateTime(),
                        rs.getString("content")
                ), id);
    }

}
