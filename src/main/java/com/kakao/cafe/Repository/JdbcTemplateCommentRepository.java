package com.kakao.cafe.Repository;

import com.kakao.cafe.Domain.Comment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class JdbcTemplateCommentRepository implements CommentRepository {

    DateTimeFormatter commentTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateCommentRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public void saveComment(Comment comment) {
        jdbcTemplate.update(
                "insert into comments(author, content, articleId) values(?,?,?)",
                comment.getAuthor(), comment.getContent(), comment.getArticleId());
    }

    @Override
    public List<Comment> findCommentsOf(Long articleId) {
        return jdbcTemplate.query("select * from comments where articleId = ?", commentRowMapper(), articleId);
    }

    @Override
    public Optional<Comment> findByCommentId(Long id) {
        List<Comment> result = jdbcTemplate.query("select * from comments where id = ?", commentRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public void editComment(Long commentId, Comment comment) {
        jdbcTemplate.update(
                "update comments set content = ? where id = ?",
                comment.getContent(), commentId);
    }

    @Override
    public void deleteComment(Long commentId) {
        jdbcTemplate.update(
                "delete from comments where id = ?", commentId);
    }


    private RowMapper<Comment> commentRowMapper() {
        return (rs, rowNum) -> {
            Comment comment = new Comment(
                    rs.getString("content"),
                    rs.getLong("articleId"));
            comment.setId(rs.getLong("id"));
            comment.setAuthor(rs.getString("author"));
            comment.setCreated(rs.getTimestamp("created").toLocalDateTime().format(commentTimeFormatter));
            return comment;
        };
    }
}
