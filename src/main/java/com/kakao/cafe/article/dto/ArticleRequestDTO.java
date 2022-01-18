package com.kakao.cafe.article.dto;

import com.kakao.cafe.article.domain.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@AllArgsConstructor
@ToString
public class ArticleRequestDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    public Article toArticle(LocalDate createDate, Long memberId) {
        return Article.builder()
                .title(title)
                .content(content)
                .createDate(createDate)
                .memberId(memberId)
                .build();
    }
}
