package com.kakao.cafe.repository;

import com.kakao.cafe.model.vo.ArticleVo;
import com.kakao.cafe.model.vo.CommentVo;
import com.kakao.cafe.model.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ArticleDao {

    private final JdbcTemplate jdbcTemplate;

    public ArticleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    Logger logger = LoggerFactory.getLogger(ArticleDao.class);

    public List<ArticleVo> findAllArticle() {
        String sql = "SELECT articles.id AS id, user_id, writer_id, name, title, contents FROM articles INNER JOIN USERS U on U.ID = ARTICLES.WRITER_ID WHERE deleted = false";
        return jdbcTemplate.query(sql, articleRowMapper());
    }

    public List<CommentVo> findAllComments(int index) {
        String sql = "SELECT comments.id AS id, user_id, writer_id, name, contents FROM comments INNER JOIN USERS U on U.id = comments.writer_id WHERE article_id = ? AND deleted = false";
        return jdbcTemplate.query(sql, commentRowMapper(), index);
    }

    public ArticleVo filterArticleByIndex(int index) {
        try {
            String sql = "SELECT articles.id AS id, user_id, writer_id, name, title, contents FROM articles INNER JOIN USERS U on U.ID = ARTICLES.WRITER_ID WHERE articles.id = ? AND deleted = false";
            return jdbcTemplate.queryForObject(sql, articleRowMapper(), index);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public CommentVo filterCommentById(int commentId) {
        try {
            String sql = "SELECT comments.id AS id, user_id, writer_id, name, contents FROM comments INNER JOIN USERS U on U.id = comments.writer_id WHERE comments.id = ? AND deleted = false";
            return jdbcTemplate.queryForObject(sql, commentRowMapper(), commentId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void writeArticle(ArticleVo article) {
        String sql = "INSERT INTO articles (title, contents, writer_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, article.getTitle(), article.getContents(), article.getWriter().getId());
    }

    public void updateArticle(int index, ArticleVo article) {
        String sql = "UPDATE articles SET title = ?, contents = ? WHERE id = ?";
        jdbcTemplate.update(sql, article.getTitle(), article.getContents(), index);
    }

    public void deleteArticle(int index) {
        String sql = "UPDATE articles SET deleted = true WHERE id = ?";
        String sql2 = "UPDATE comments SET deleted = true WHERE article_id = ?";
        jdbcTemplate.update(sql, index);
        jdbcTemplate.update(sql2, index);
    }

    public void writerComment(int index, CommentVo comment) {
        String sql = "INSERT INTO comments (contents, writer_id, article_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, comment.getContents(), comment.getWriter().getId(), index);
    }

    public void deleteComment(int commentId) {
        String sql = "UPDATE comments SET deleted = true WHERE id = ?";
        jdbcTemplate.update(sql, commentId);
    }

    private RowMapper<ArticleVo> articleRowMapper() {
        return (rs, rowNum) -> new ArticleVo(
                rs.getInt("id"),
                new UserVo(rs.getInt("writer_id"), rs.getString("user_id"), rs.getString("name")),
                rs.getString("title"),
                rs.getString("contents")
        );
    }

    private RowMapper<CommentVo> commentRowMapper() {
        return (rs, rowNum) -> new CommentVo(
                rs.getInt("id"),
                new UserVo(rs.getInt("writer_id"), rs.getString("user_id"), rs.getString("name")),
                rs.getString("contents")
        );
    }
}
