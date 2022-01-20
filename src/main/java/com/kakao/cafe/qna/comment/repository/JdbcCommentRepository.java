package com.kakao.cafe.qna.comment.repository;

import com.kakao.cafe.qna.comment.Comment;
import com.kakao.cafe.qna.comment.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by melodist
 * Date: 2022-01-19 019
 * Time: 오후 6:23
 */
@Repository
public class JdbcCommentRepository implements CommentRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcCommentRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Comment insert(Comment comment) {
        String sql = "INSERT INTO COMMENTS (WRITER_ID, WRITER, ARTICLE_ID, " +
                "CONTENTS, IS_DELETED, CREATED_DATE, MODIFIED_DATE)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                comment.getWriterId(),
                comment.getWriter(),
                comment.getArticleId(),
                comment.getContents(),
                comment.getIsDeleted() ? "Y" : "N",
                comment.getCreatedDate(),
                comment.getModifiedDate());
        return comment;
    }

    @Override
    public Comment update(Comment comment) {
        String sql = "UPDATE COMMENTS SET IS_DELETED=?, MODIFIED_DATE=? WHERE ID=?";
        jdbcTemplate.update(sql,
                comment.getIsDeleted() ? "Y" : "N",
                comment.getModifiedDate(),
                comment.getId());
        return comment;
    }

    @Override
    public Comment findCommentById(Integer articleId, Integer commentId) {
        String sql = "SELECT * FROM COMMENTS WHERE ID = ? AND ARTICLE_ID = ? AND IS_DELETED = 'N'";
        return jdbcTemplate.queryForObject(sql, new CommentMapper(), commentId, articleId);
    }

    @Override
    public List<Comment> findCommentsByArticleId(Integer articleId) {
        String sql = "SELECT * FROM COMMENTS WHERE ARTICLE_ID = ? AND IS_DELETED = 'N'";
        return jdbcTemplate.query(sql, new CommentMapper(), articleId);
    }
}
