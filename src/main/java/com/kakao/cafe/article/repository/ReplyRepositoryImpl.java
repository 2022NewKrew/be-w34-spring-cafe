package com.kakao.cafe.article.repository;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kakao.cafe.article.entity.Reply;
import com.kakao.cafe.article.entity.mapper.ReplyRowMapper;

@Repository
@RequiredArgsConstructor
public class ReplyRepositoryImpl implements ReplyRepository {
    private static final String SAVE_QUERY = "INSERT INTO reply(article_id, writer, contents) VALUES (?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM reply WHERE id = ? AND is_deleted = false";
    private static final String FIND_ALL_BY_USER_ID_QUERY = "SELECT * FROM reply WHERE article_id = ? AND is_deleted = false";
    private static final String DELETE_QUERY = "UPDATE reply SET is_deleted = true, modify_time = CURRENT_TIME WHERE id = ?";


    private final JdbcTemplate jdbcTemplate;
    private final ReplyRowMapper replyRowMapper;

    @Override
    public void save(Reply reply) {
        jdbcTemplate.update(SAVE_QUERY, reply.getArticleId(), reply.getWriter(), reply.getContents());
    }

    @Override
    public Optional<Reply> findById(int id) {
        List<Reply> reply = this.jdbcTemplate.query(FIND_BY_ID_QUERY, this.replyRowMapper, id);

        return reply.stream().findFirst();
    }

    @Override
    public List<Reply> findAllByArticleId(int articleId) {
        return jdbcTemplate.query(FIND_ALL_BY_USER_ID_QUERY, replyRowMapper, articleId);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE_QUERY, id);
    }
}
