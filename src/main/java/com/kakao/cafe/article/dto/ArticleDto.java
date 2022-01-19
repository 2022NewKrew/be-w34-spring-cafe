package com.kakao.cafe.article.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ArticleDto {
    private Long id;
    @NotBlank(message = "제목은 필수 입력값 입니다.")
    private String title;
    private String content;
    private LocalDateTime createTime;

    @Builder
    public ArticleDto(Long id, String title, String content, LocalDateTime createTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
    }
}
