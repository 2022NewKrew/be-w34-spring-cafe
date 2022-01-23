package com.kakao.cafe.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReplyDto {
    private Long id;
    private String content;
    private String writer;
    private String writeDate;
    private Long userId;
    private Long articleId;
}
