package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.model.Comment;
import com.kakao.cafe.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CommentJdbcRepository implements CommentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Comment comment){
        String sql = "INSERT INTO COMMENTS (articleId, author, contents, uploadTime) VALUES ( ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                comment.getArticleId(), comment.getAuthor(),
                comment.getContents(), DateUtils.getCurrentDate());
    }

    @Override
    public List<Comment> findAllByArticleId(Long articleId) {
        String sql = "SELECT * FROM COMMENTS WHERE articleId=? AND deleted=false";
        return jdbcTemplate.query(sql, commentRowMapper(), articleId);
    }

    @Override
    public void deleteCommentById(Long id) {
        String sql = "UPDATE COMMENTS SET deleted=true WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void deleteByArticleId(Long articleId) {
        String sql = "UPDATE COMMENTS SET deleted=true WHERE articleId=?";
        jdbcTemplate.update(sql, articleId);
    }

    @Override
    public Optional<Comment> findOneById(Long id) {
        String sql = "SELECT * FROM COMMENTS WHERE id=?";
        return jdbcTemplate.query(sql, commentRowMapper(), id).stream().findAny();
    }

    public RowMapper<Comment> commentRowMapper() {
        return (rs, rowNum) -> new Comment(
                rs.getLong("id"),
                rs.getLong("articleId"),
                rs.getString("author"),
                rs.getString("contents"),
                rs.getString("uploadTime"),
                rs.getBoolean("deleted")
        );
    }

}
