package com.kakao.cafe.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommentDTO {
    private Long key;
    private Long articleKey;
    private Long authorKey;
    private String authorName;
    private String content;
    private String postTime;
}
