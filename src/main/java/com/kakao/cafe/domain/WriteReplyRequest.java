package com.kakao.cafe.domain;

import java.util.Date;
import javax.validation.constraints.NotBlank;

public class WriteReplyRequest {
    private static final int INITIAL_ID = 0;
    @NotBlank(message = "댓글 내용이 비어있습니다")
    private final String comment;

    public WriteReplyRequest(String comment) {
        this.comment = comment;
    }

    public Reply toEntity(int userId, int postId) {
        return new Reply(INITIAL_ID, userId, postId, comment, new Date());
    }
}
