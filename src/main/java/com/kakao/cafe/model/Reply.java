package com.kakao.cafe.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Reply {
    int replyId;
    int postId;
    String userId;
    String content;
    String createdAt;

    public Reply() {}
    public Reply(int replyId, int postId, String userId, String content, String createdAt) {
        this.replyId = replyId;
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.createdAt = createdAt;
    }
}
