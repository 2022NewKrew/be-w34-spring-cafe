package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcCommentRepositoryImpl implements CommentRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcCommentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Comment comment) {
        try {
            jdbcTemplate.queryForObject("SELECT id FROM COMMENT WHERE id = ?", Integer.class, comment.getId());
            jdbcTemplate.update("UPDATE COMMENT " +
                    "SET writer = ?, contents = ?, qna_index = ?, created_at = ?, deleted = ? " +
                    "WHERE id = ?", comment.getWriter(), comment.getContents(), comment.getQnaIndex(), comment.getCreatedAt(), comment.getDeleted(), comment.getId());
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            jdbcTemplate.update("INSERT INTO COMMENT(writer, contents, qna_index, created_at, deleted) " +
                    "VALUES (?, ?, ?, ?, ?)", comment.getWriter(), comment.getContents(), comment.getQnaIndex(), comment.getCreatedAt(), comment.getDeleted());
        }
    }

    @Override
    public List<Comment> findByQnaIndexAndDeleted(Integer qnaIndex, Boolean isDeleted) {
        return jdbcTemplate.query("SELECT id, writer, contents, qna_index, created_at " +
                "FROM COMMENT " +
                "WHERE qna_index = ? AND deleted = ? ", this::commentMapRow, qnaIndex, isDeleted);
    }

    @Override
    public Optional<Comment> findById(Integer id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject("SELECT id, writer, contents, qna_index, created_at " +
                    "FROM COMMENT " +
                    "WHERE id = ?", this::commentMapRow, id));

        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return Optional.empty();
        }
    }

    private Comment commentMapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new Comment(resultSet.getInt("id"), resultSet.getString("writer"),
                resultSet.getString("contents"), resultSet.getInt("qna_index"),
                resultSet.getTimestamp("created_at").toLocalDateTime());
    }
}
