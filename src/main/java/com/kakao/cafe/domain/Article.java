package com.kakao.cafe.domain;

import com.kakao.cafe.dto.article.ArticleCreateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
public class Article {

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

    private String contents;

    public Article(Long id, ArticleCreateDTO articleCreateDTO){
        this.id = id;
        this.writingTime = LocalDateTime.now();
        this.countOfComment = 0L;
        this.authorId = articleCreateDTO.getAuthorId();
        this.title = articleCreateDTO.getTitle();
        this.contents = articleCreateDTO.getContents();
    }
}
