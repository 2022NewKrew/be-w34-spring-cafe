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

    /*TODO 게시글 삭제시 코멘트도 같이 삭제 되어야 할까?
     게시글 복구시 남은 코멘트를 살리고 싶다면?
     게시글 복구를 할필요가 없다면?
     요구사항 분석에 따라 아래 함수 필요성이 달라질듯
     */
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
}
