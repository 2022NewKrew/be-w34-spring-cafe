package com.kakao.cafe.model.repository;

import com.kakao.cafe.model.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository("CommentRepositoryJdbcImpl")
public class CommentRepositoryJdbcImpl implements CommentRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Comment> commentRowMapper() {
        return (rs, rowNum) ->
                Comment.builder()
                        .articleId(rs.getLong("ARTICLE_ID"))
                        .commentId(rs.getInt("COMMENT_ID"))
                        .writerId(rs.getString("WRITER_ID"))
                        .content(rs.getString("CONTENT"))
                        .createdDate(rs.getTimestamp("CREATED_DATE").toLocalDateTime())
                        .formattedCreatedDate(rs.getTimestamp("CREATED_DATE").toLocalDateTime()
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .isDeleted(rs.getBoolean("IS_DELETED")).build();
    }

    @Override
    public Optional<Comment> saveComment(long articleId, Comment comment) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("COMMENTS").usingGeneratedKeyColumns("COMMENT_ID");
        comment.setCreatedDate(LocalDateTime.now());
        comment.setFormattedCreatedDate(comment.getCreatedDate()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ARTICLE_ID", articleId);
        parameters.put("WRITER_ID", comment.getWriterId());
        parameters.put("CONTENT", comment.getContent());
        parameters.put("CREATED_DATE", comment.getCreatedDate());
        parameters.put("IS_DELETED", comment.isDeleted());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        comment.setCommentId(key.longValue());
        return Optional.of(comment);
    }

    @Override
    public List<Comment> findCommentsByArticleId(long articleId) {
        return jdbcTemplate.query("SELECT ARTICLE_ID, COMMENT_ID, WRITER_ID, CONTENT, CREATED_DATE, IS_DELETED FROM COMMENTS WHERE ARTICLE_ID = ? AND IS_DELETED = FALSE",
                commentRowMapper(), articleId);
    }

    @Override
    public Optional<Comment> findCommentByArticleIdAndCommentId(long articleId, long commentId) {
        List<Comment> result = jdbcTemplate.query("SELECT ARTICLE_ID, COMMENT_ID, WRITER_ID, CONTENT, CREATED_DATE, IS_DELETED FROM COMMENTS WHERE ARTICLE_ID = ? AND COMMENT_ID = ? AND IS_DELETED = FALSE",
                commentRowMapper(), articleId, commentId);
        return result.stream().findAny();
    }

    @Override
    public List<Comment> findCommentsByWriterId(String writerId) {
        return jdbcTemplate.query("SELECT ARTICLE_ID, COMMENT_ID, WRITER_ID, CONTENT, CREATED_DATE, IS_DELETED FROM COMMENTS WHERE WRITER_ID = ? AND IS_DELETED = FALSE",
                commentRowMapper(), writerId);
    }

    @Override
    public boolean modifyComment(long articleId, Comment comment) {
        jdbcTemplate.update("UPDATE COMMENTS SET CONTENT = ? WHERE ARTICLE_ID = ? AND COMMENT_ID = ?",
                comment.getContent(), articleId, comment.getCommentId());
        return true;
    }

    @Override
    public boolean deleteCommentByArticleId(long articleId) {
        jdbcTemplate.update("UPDATE COMMENTS SET IS_DELETED = TRUE WHERE ARTICLE_ID = ?", articleId);
        return true;
    }

    @Override
    public boolean deleteComment(long articleId, long commentId) {
        jdbcTemplate.update("UPDATE COMMENTS SET IS_DELETED = TRUE WHERE ARTICLE_ID = ? AND COMMENT_ID = ?", articleId, commentId);
        return true;
    }
}
