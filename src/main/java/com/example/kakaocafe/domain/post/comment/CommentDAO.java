package com.example.kakaocafe.domain.post.comment;

import com.example.kakaocafe.domain.post.comment.dto.WriteCommentForm;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentDAO {

    private final JdbcTemplate jdbcTemplate;

    public void create(WriteCommentForm writeCommentForm) {
        final String sql = "INSERT INTO `comment` (`post_id`, `user_id`, `contents`) VALUES(?,?,?)";

        final Object[] params = {
                writeCommentForm.getPostId(),
                writeCommentForm.getWriterId(),
                writeCommentForm.getContents()
        };

        jdbcTemplate.update(sql, params);
    }
}
