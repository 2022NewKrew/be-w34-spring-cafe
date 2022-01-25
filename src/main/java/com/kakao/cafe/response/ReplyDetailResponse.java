package com.kakao.cafe.response;

import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.domain.User;
import java.util.Date;

public class ReplyDetailResponse {

    private final int replyId;
    private final String name;
    private final String comment;
    private final Date createdAt;

    private ReplyDetailResponse(int replyId, String name, String comment,
            Date createdAt) {
        this.replyId = replyId;
        this.name = name;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public static ReplyDetailResponse of(Reply reply, User user) {
        return new ReplyDetailResponse(reply.getId(), user.getUserName(), reply.getComment(), reply.getCreatedAt());
    }
}
