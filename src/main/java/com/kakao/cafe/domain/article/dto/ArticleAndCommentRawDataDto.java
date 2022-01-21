package com.kakao.cafe.domain.article.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

// Repository에서 join 한 row 들을 담는 DTO
@Getter
@Setter
public class ArticleAndCommentRawDataDto {

    private Long id;
    private String authorUserId;
    private String title;
    private String contents;
    private LocalDateTime articleRegisterDateTime;

    private Long commentId;
    private String commenterUserId;
    private String comment;
    private LocalDateTime commentTime;

}

