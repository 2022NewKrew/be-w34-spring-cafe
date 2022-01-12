package com.kakao.cafe.domain;

import com.kakao.cafe.dto.ArticleDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Builder
public class Article {
    private long key;
    private String author;
    private String title;
    private String content;
    private LocalDateTime postTime;

    public static Article fromDTOWithoutPostTime(ArticleDTO articleDTO) {
        Article article = Article.builder()
                .key(articleDTO.getKey())
                .author(articleDTO.getAuthor())
                .title(articleDTO.getTitle())
                .content(articleDTO.getContent())
                .build();
        return article;
    }

    public ArticleDTO getDTO() {
        ArticleDTO articleDTO = ArticleDTO.builder()
                .key(key)
                .author(author)
                .title(title)
                .content(content)
                .postTime(postTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
        return articleDTO;
    }


}
