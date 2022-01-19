package com.kakao.cafe.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private long articleId;
    private int commentId;
    private String writerId;
    private String content;
    private LocalDateTime createdDate;
    boolean isDeleted;

    public String getFormattedCreatedDate() {
        return createdDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
