package com.kakao.cafe.domain.Repository.reply;

import com.kakao.cafe.domain.Entity.Comment;
import com.kakao.cafe.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final JdbcTemplate jdbcTemplate;
    private final CommentMapper commentMapper;

    public void save(Comment comment) {
        this.jdbcTemplate.update("INSERT INTO COMMENTS (articleId, userId, writer, contents) VALUE (?, ?, ?, ?)",
                comment.getArticleId(), comment.getUserId(), comment.getWriter(), comment.getContents());
    }

    public List<Comment> findAllByArticleIdAndNotDeleted(int articleId) {
        return this.jdbcTemplate.query("SELECT * FROM COMMENTS WHERE articleId = ? AND deleted != 1", this.commentMapper, articleId);
    }

    public Comment findById(int commentId) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM COMMENTS WHERE id = ?", this.commentMapper, commentId);
    }

    public void delete(int commentId) {
        this.jdbcTemplate.update("UPDATE COMMENTS SET deleted = 1 WHERE id = ?", commentId);
    }

}
