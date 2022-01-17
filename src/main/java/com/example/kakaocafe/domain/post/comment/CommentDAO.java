package com.example.kakaocafe.domain.post.comment;

import com.example.kakaocafe.domain.post.comment.dto.WriteCommentForm;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class CommentDAO {

    private final JdbcTemplate jdbcTemplate;

    public void create(WriteCommentForm writeCommentForm) {
        final String sql = "INSERT INTO COMMENT (POST_ID, USER_ID, CONTENTS) VALUES(?,?,?)";

        final Object[] params = {
                writeCommentForm.getPostId(),
                writeCommentForm.getWriterId(),
                writeCommentForm.getContents()
        };

        jdbcTemplate.update(sql, params);
    }

    public void delete(long commentId) {
        final String sql = "UPDATE COMMENT SET ISDELETED=true WHERE ID=?";

        jdbcTemplate.update(sql, commentId);
    }

    public void deleteAllByPostId(long postId) {
        final String sql = "UPDATE COMMENT " +
                "SET ISDELETED= true " +
                "WHERE ID in (SELECT id FROM COMMENT WHERE POST_ID = ? AND ISDELETED = false)";

        jdbcTemplate.update(sql, postId);
    }

    public boolean canDelete(long postId, long commentWriterId) {
        final String sql = "SELECT EXISTS( " +
                "               SELECT ID " +
                "               FROM COMMENT " +
                "               WHERE POST_ID = ? " +
                "                 AND NOT USER_ID = ?" +
                "                 AND ISDELETED=false) ";

        final Boolean isNotExist = jdbcTemplate.queryForObject(sql, Boolean.class, postId, commentWriterId);
        Objects.requireNonNull(isNotExist);

        return isNotExist;
    }

    public boolean isNotWriter(long postId, long commentId, long writerId) {
        final String sql = "SELECT EXISTS(SELECT ID FROM COMMENT WHERE ID=? AND POST_ID=? AND USER_ID=?)";

        final Boolean isExist = jdbcTemplate.queryForObject(sql, Boolean.class, commentId, postId, writerId);
        Objects.requireNonNull(isExist);

        return !isExist;
    }
}
