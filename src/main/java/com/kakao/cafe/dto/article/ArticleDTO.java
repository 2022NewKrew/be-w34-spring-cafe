package com.kakao.cafe.dto.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ArticleDTO {

    @NotBlank
    private Long id;

    @NotBlank
    private LocalDateTime writingTime;

    @NotBlank
    private Long countOfComment;

    @NotBlank
    private String authorId;

    @NotBlank
    private String title;

    @NotBlank
    private String contents;
}
