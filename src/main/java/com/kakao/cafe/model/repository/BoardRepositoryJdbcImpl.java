package com.kakao.cafe.model.repository;

import com.kakao.cafe.model.domain.Article;
import com.kakao.cafe.model.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BoardRepositoryJdbcImpl implements BoardRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BoardRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) ->
                Article.builder()
                        .articleId(rs.getLong("ARTICLE_ID"))
                        .title(rs.getString("TITLE"))
                        .writerId(rs.getString("WRITER_ID"))
                        .content(rs.getString("CONTENT"))
                        .createdDate(rs.getTimestamp("CREATED_DATE").toLocalDateTime()).build();
    }

    private RowMapper<Comment> commentRowMapper() {
        return (rs, rowNum) ->
                Comment.builder()
                        .articleId(rs.getLong("ARTICLE_ID"))
                        .commentId(rs.getInt("COMMENT_ID"))
                        .writerId(rs.getString("WRITER_ID"))
                        .content(rs.getString("CONTENT"))
                        .createdDate(rs.getTimestamp("CREATED_DATE").toLocalDateTime()).build();
    }

    @Override
    public boolean saveArticle(Article article) {
        jdbcTemplate.update("INSERT INTO ARTICLES (TITLE, WRITER_ID, CONTENT, CREATED_DATE) VALUES ( ?, ?, ?, ? )",
                article.getTitle(), article.getWriterId(), article.getContent(), LocalDateTime.now());
        return true;
    }

    @Override
    public boolean saveComment(long articleId, Comment comment) {
        jdbcTemplate.update("INSERT INTO COMMENTS (ARTICLE_ID, WRITER_ID, CONTENT, CREATED_DATE) VALUES ( ?, ?, ?, ? )",
                articleId, comment.getWriterId(), comment.getContent(), LocalDateTime.now());
        return true;
    }

    @Override
    public List<Article> findAllArticle() {
        return jdbcTemplate.query("SELECT ARTICLE_ID, TITLE, WRITER_ID, CONTENT, CREATED_DATE FROM ARTICLES",
                articleRowMapper());
    }

    @Override
    public Optional<Article> findArticleByArticleId(long articleId) {
        List<Article> result = jdbcTemplate.query("SELECT ARTICLE_ID, TITLE, WRITER_ID, CONTENT, CREATED_DATE FROM ARTICLES WHERE ARTICLE_ID = ?",
                articleRowMapper(), articleId);
        return result.stream().findAny();
    }

    @Override
    public List<Article> findArticlesByWriterId(String writerId) {
        return jdbcTemplate.query("SELECT ARTICLE_ID, TITLE, WRITER_ID, CONTENT, CREATED_DATE FROM ARTICLES WHERE WRITER_ID = ?",
                articleRowMapper(), writerId);
    }

    @Override
    public List<Comment> findCommentsByArticleId(long articleId) {
        return jdbcTemplate.query("SELECT ARTICLE_ID, COMMENT_ID, WRITER_ID, CONTENT, CREATED_DATE FROM COMMENTS WHERE ARTICLE_ID = ?",
                commentRowMapper(), articleId);
    }

    @Override
    public Optional<Comment> findComment(long articleId, long commentId) {
        List<Comment> result = jdbcTemplate.query("SELECT ARTICLE_ID, COMMENT_ID, WRITER_ID, CONTENT, CREATED_DATE FROM COMMENTS WHERE ARTICLE_ID = ? AND COMMENT_ID = ?",
                commentRowMapper(), articleId, commentId);
        return result.stream().findAny();
    }

    @Override
    public boolean modifyArticle(Article article) {
        jdbcTemplate.update("UPDATE ARTICLES SET TITLE = ?, CONTENT = ? WHERE ARTICLE_ID = ?",
                article.getTitle(), article.getContent(), article.getArticleId());
        return true;
    }

    @Override
    public boolean modifyComment(long articleId, Comment comment) {
        jdbcTemplate.update("UPDATE COMMENTS SET CONTENT = ? WHERE ARTICLE_ID = ? AND COMMENT_ID = ?",
                comment.getContent(), articleId, comment.getCommentId());
        return true;
    }

    @Override
    public boolean deleteArticle(long articleId) {
        jdbcTemplate.update("DELETE FROM ARTICLES WHERE ARTICLE_ID = ?", articleId);
        return true;
    }

    @Override
    public boolean deleteComment(long articleId, long commentId) {
        jdbcTemplate.update("DELETE FROM COMMENTS WHERE ARTICLE_ID = ? AND COMMENT_ID = ?", articleId, commentId);
        return true;
    }
}
