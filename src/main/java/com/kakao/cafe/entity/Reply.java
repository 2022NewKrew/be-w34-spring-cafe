package com.kakao.cafe.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reply {
    private Long replyId;
    private String content;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public void changeContent(String content) {
        this.content = content;
    }

    private User writer;
    private Article article;
}
