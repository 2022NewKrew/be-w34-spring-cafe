package com.kakao.cafe.repository.reply;

import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.repository.reply.mapper.ReplyRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcReplyRepository implements ReplyRepository{

    private final JdbcTemplate jdbcTemplate;
    private final ReplyRowMapper replyRowMapper;

    private static final String SELECT_BY_ARTICLE_ID_QUERY
            = "SELECT r.id as id, article_id, writer_id, u.user_name as writer_name, comment, created_time, updated_time FROM replies as r INNER JOIN users as u ON r.writer_id = u.user_id WHERE article_id = ?";

    @Override
    public List<Reply> findByArticleId(Long articleId) {
        return jdbcTemplate.query(SELECT_BY_ARTICLE_ID_QUERY, replyRowMapper, articleId);
    }
}
