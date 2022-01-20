package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReplyDto {
    private Long articleId;
    private String writerEmail;
    private String writerUsername;
    private Long replyId;
    private String content;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
