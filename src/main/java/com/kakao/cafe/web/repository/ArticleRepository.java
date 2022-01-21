package com.kakao.cafe.web.repository;

import com.kakao.cafe.domain.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleRepository {
    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;
    private final ReplyRepository replyRepository;

    public ArticleRepository(JdbcTemplate jdbcTemplate, UserRepository userRepository, ReplyRepository replyRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
        this.replyRepository = replyRepository;
    }

    public List<Article> selectAllArticles() {
        return jdbcTemplate.query(QueryConstants.ARTICLE_SELECT,
                (rs, rowNum) -> new Article(
                        rs.getInt("id"),
                        userRepository.selectByUserId(rs.getInt("author_id")),
                        new java.sql.Timestamp(rs.getTimestamp("createdate").getTime()).toLocalDateTime(),
                        rs.getString("title"),
                        rs.getString("content"),
                        replyRepository.selectReplies(rs.getInt("id"))
                ));
    }

    public void insertArticle(Article article) {
        jdbcTemplate.update(QueryConstants.ARTICLE_INSERT, article.getAuthor().getId(), article.getTitle(), article.getContent());
    }

    public Article selectByArticleId(int id) {
        return jdbcTemplate.queryForObject(QueryConstants.ARTICLE_SELECT_BY_ID,
                (rs, rowNum) -> new Article(
                        rs.getInt("id"),
                        userRepository.selectByUserId(rs.getInt("author_id")),
                        new java.sql.Timestamp(rs.getTimestamp("createdate").getTime()).toLocalDateTime(),
                        rs.getString("title"),
                        rs.getString("content"),
                        replyRepository.selectReplies(rs.getInt("id"))
                ), id);
    }

    public void updateArticle(int id, Article updateArticle) {
        jdbcTemplate.update(QueryConstants.ARTICLE_UPDATE, updateArticle.getTitle(), updateArticle.getContent(), id);
    }

    public void deleteArticle(int id) {
        jdbcTemplate.update(QueryConstants.ARTICLE_DELETE, id);
    }
}
