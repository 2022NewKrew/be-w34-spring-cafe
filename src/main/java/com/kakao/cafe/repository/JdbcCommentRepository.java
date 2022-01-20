package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Comment;
import com.kakao.cafe.web.dto.CommentCreateRequestDto;
import org.apache.tomcat.jni.Local;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Repository
public class JdbcCommentRepository implements CommentRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCommentRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(CommentCreateRequestDto requestDto) {
        String sql = "INSERT INTO comments (article_id, commenter, contents, deleted, created_at, modified_at) VALUES (?, ?, ?, false, ?, ?)";
        jdbcTemplate.update(sql, requestDto.getArticleId(), requestDto.getCommenter(), requestDto.getContents(), requestDto.getCreatedAt(), requestDto.getModifiedAt());
    }

    @Override
    public List<Comment> findByArticleIdNotDeleted(Long articleId) {
        String sql = "SELECT * FROM comments WHERE article_id = ? AND deleted = FALSE";
        try {
            return jdbcTemplate.query(sql, commentRowMapper(), articleId);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("해당하는 댓글이 존재하지 않습니다.");
        }
    }

    @Override
    public void shiftIsDeleted(Long id) {

    }

    private RowMapper<Comment> commentRowMapper() {
        return (rs, rowNum) -> new Comment(rs.getLong("id"),
                rs.getLong("article_id"),
                rs.getString("commenter"),
                rs.getString("contents"),
                rs.getTimestamp("created_at").toLocalDateTime().truncatedTo(ChronoUnit.SECONDS),
                rs.getTimestamp("modified_at").toLocalDateTime().truncatedTo(ChronoUnit.SECONDS)
        );
    }

}
