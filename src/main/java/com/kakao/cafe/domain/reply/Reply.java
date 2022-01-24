package com.kakao.cafe.domain.reply;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Reply {
    private Long id;
    private String userId;
    private String comment;
    private Long postId;
    private LocalDateTime regDateTime;

    public Reply() {
    }

    @Builder
    public Reply(String userId, String comment, Long postId) {
        this.userId = userId;
        this.comment = comment;
        this.postId = postId;
        regDateTime = LocalDateTime.now();
    }
}
