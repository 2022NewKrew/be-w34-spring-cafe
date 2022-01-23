package com.kakao.cafe.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ReplyEntity extends BaseEntity {
    private Long id;
    private String content;
    private String writer;
    private LocalDateTime writeDate;
    private Long userId;
    private Long articleId;

    public void putReplyId(Long id) {
        this.id = id;
    }

}
