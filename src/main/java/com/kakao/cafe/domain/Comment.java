package com.kakao.cafe.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
public class Comment {
    private Long id;
    private String contents;
    private Long postId;
    private Long userId;
    private OffsetDateTime createdAt;
}
