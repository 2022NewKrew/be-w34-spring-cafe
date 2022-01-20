package com.kakao.cafe.reply.domain;


import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Reply {
    private Long id;
    private Long articleId;
    private Long authorId;
    private String authorStringId;
    private String contents;
    private LocalDateTime writeTime;
    private Boolean isAvailable;

    @Builder
    private Reply(Long id, Long articleId, Long authorId, String authorStringId, String contents,
                 LocalDateTime writeTime, Boolean isAvailable) {
        this.id = id;
        this.articleId = articleId;
        this.authorId = authorId;
        this.authorStringId = authorStringId;
        this.contents = contents;
        this.writeTime = writeTime;
        this.isAvailable = isAvailable;
    }
}
