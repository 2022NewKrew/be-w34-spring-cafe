package com.kakao.cafe.domain.post;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@ToString
public class Post {
    private Long id;
    private String writer;
    private String title;
    private String body;
    private LocalDateTime createdAt;
}
