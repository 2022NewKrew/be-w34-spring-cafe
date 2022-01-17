package com.example.kakaocafe.domain.post.comment;

import com.example.kakaocafe.domain.post.comment.dto.Comment;
import com.example.kakaocafe.domain.post.comment.dto.WriteCommentForm;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    public List<Comment> findAllByPostId(long postId) {
        final String sql = "SELECT c.ID                                          as c_id, " +
                "       FORMATDATETIME(c.created, 'yyyy-MM-dd hh:mm') as c_created, " +
                "       u.NAME                                        as c_writer, " +
                "       c.CONTENTS                                    as c_contents, " +
                "       c.POST_ID                                     as p_id " +
                "FROM COMMENT c " +
                "         inner join USER U on U.ID = c.USER_ID " +
                "WHERE c.POST_ID = ? " +
                "  AND c.ISDELETED = false ";

        return jdbcTemplate.query(sql, commentRowMapper(), postId);
    }

    public void delete(long commentId) {
        final String sql = "UPDATE COMMENT SET ISDELETED=true WHERE ID=?";

        jdbcTemplate.update(sql, commentId);
    }

    public void deleteAllByPostId(long postId) {
        final String sql = "UPDATE COMMENT " +
                "SET ISDELETED = true " +
                "WHERE ID in (SELECT id FROM COMMENT WHERE POST_ID = ? AND ISDELETED = false)";

        jdbcTemplate.update(sql, postId);
    }

    public boolean isNotWriter(long postId, long commentId, long writerId) {
        final String sql = "SELECT NOT EXISTS(SELECT ID FROM COMMENT WHERE ID=? AND POST_ID=? AND USER_ID=?)";

        final Boolean isNotExist = jdbcTemplate.queryForObject(sql, Boolean.class, commentId, postId, writerId);
        Objects.requireNonNull(isNotExist);

        return isNotExist;
    }

    private RowMapper<Comment> commentRowMapper() {
        return (rs, rowNum) -> {
            final long postId = rs.getLong("p_id");
            final long id = rs.getLong("c_id");
            final String created = rs.getString("c_created");
            final String contents = rs.getString("c_contents");
            final String writer = rs.getString("c_writer");

            return new Comment(id, postId, writer, contents, created);
        };
    }
}
