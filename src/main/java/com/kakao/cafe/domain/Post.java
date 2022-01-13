package com.kakao.cafe.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Post {
    private Long id;

    @Builder.Default
    private String title = "";

    @Builder.Default
    private String contents = "";

    @Builder.Default
    private LocalDateTime dateCreated = LocalDateTime.now();

    @Builder.Default
    private Integer viewNum = 0;

    private Long userId;
}
