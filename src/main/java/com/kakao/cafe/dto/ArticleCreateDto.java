package com.kakao.cafe.dto;

import com.kakao.cafe.model.Article;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


@Getter
public class ArticleCreateDto {

    @NotBlank(message = "글쓴이를 입력해주세요")
    private final String writer;
    @NotBlank(message = "제목을 입력해주세요")
    private final String title;
    @NotBlank(message = "내용을 입력해주세요")
    private final String contents;
    private Integer articleSequence = 0;

    public ArticleCreateDto(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public Article toEntity() {
        return Article.builder().id(articleSequence++)
                .writer(writer)
                .title(title)
                .contents(contents)
                .createTime(LocalDateTime.now())
                .build();
    }

}
