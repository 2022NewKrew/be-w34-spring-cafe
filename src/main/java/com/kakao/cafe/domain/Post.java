package com.kakao.cafe.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
public class Post {
    private Long id;

    @Builder.Default
    private String title = "";

    @Builder.Default
    private String contents = "";

    private OffsetDateTime createdAt;

    @Builder.Default
    private Integer viewNum = 0;

    private Long userId;
}
