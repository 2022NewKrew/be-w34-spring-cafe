package com.kakao.cafe.article.repository;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kakao.cafe.article.entity.ArticleReply;
import com.kakao.cafe.article.entity.mapper.ArticleReplyRowMapper;

@Repository
@RequiredArgsConstructor
public class ArticleReplyRepositoryImpl implements ArticleReplyRepository {
    private static final String SAVE_QUERY = "INSERT INTO reply(article_id, writer, contents) VALUES (?, ?, ?)";
    private static final String FIND_ALL_BY_USER_ID_QUERY = "SELECT * FROM reply WHERE article_id = ? AND is_deleted = false";
    private static final String DELETE_QUERY = "UPDATE reply SET is_deleted = true, modify_time = CURRENT_TIME WHERE id = ?";


    private final JdbcTemplate jdbcTemplate;
    private final ArticleReplyRowMapper articleReplyRowMapper;

    @Override
    public void save(ArticleReply reply) {
        jdbcTemplate.update(SAVE_QUERY, reply.getArticleId(), reply.getWriter(), reply.getContents());
    }

    @Override
    public List<ArticleReply> findAllByArticleId(int articleId) {
        return jdbcTemplate.query(FIND_ALL_BY_USER_ID_QUERY, articleReplyRowMapper, articleId);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE_QUERY, id);
    }
}
