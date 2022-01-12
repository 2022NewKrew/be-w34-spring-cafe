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
    private String title;
    private String contents;
    private LocalDateTime dateCreated;
    @Builder.Default
    private Integer viewNum = 0;
    private Long userId;
}
