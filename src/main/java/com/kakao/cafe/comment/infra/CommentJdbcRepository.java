package com.kakao.cafe.comment.infra;

import com.kakao.cafe.comment.domain.Comment;
import com.kakao.cafe.comment.domain.CommentRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentJdbcRepository implements CommentRepository {

    private final JdbcTemplate jdbcTemplate;

    public CommentJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Comment> findAllByArticleId(int articleId) {
        String query = "select * from comments where article_id = ?";
        return jdbcTemplate.query(query, mapCommentRow(), articleId);
    }

    @Override
    public int save(Comment comment) {
        String query = "insert into comments(article_id, author_id, content, created_at) values(?, ?, ?, ?)";

        return jdbcTemplate.update(
                query,
                comment.getArticleId(),
                comment.getAuthorId(),
                comment.getContent(),
                comment.getCreatedAt()
        );
    }

    @Override
    public Comment findByIdOrNull(String commentId) {
        String query = "select * from comments where id = ?";
        try {
            return jdbcTemplate.queryForObject(query, mapCommentRow(), commentId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void delete(Comment comment) {
        int commentId = comment.getId();
        String query = "delete from comments where id = ?";
        jdbcTemplate.update(query, commentId);
    }

    private RowMapper<Comment> mapCommentRow() {
        return (rs, rowNum) ->
                Comment.valueOf(
                        rs.getInt("id"),
                        rs.getInt("article_id"),
                        rs.getString("author_id"),
                        rs.getString("content"),
                        rs.getString("created_at")
                );
    }
}
