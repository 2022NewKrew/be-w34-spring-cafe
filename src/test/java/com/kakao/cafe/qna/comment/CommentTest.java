package com.kakao.cafe.qna.comment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by melodist
 * Date: 2022-01-20 020
 * Time: 오후 3:32
 */
class CommentTest {

    @Test
    @DisplayName("댓글 삭제")
    public void deleteComment() throws Exception {
        // given
        Comment comment = new Comment(1, 1, "test", "test");

        // when
        comment.deleteComment();

        // then
        assertThat(comment.getIsDeleted()).isTrue();
    }
}
