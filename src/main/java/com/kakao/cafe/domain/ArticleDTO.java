package com.kakao.cafe.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * author    : brody.moon
 * version   : 1.0
 * Article 클래스에서 로직을 제거하고 데이터 전송을 위해 따로 만든 클래스입니다.
 */
@Data
@NoArgsConstructor
public class ArticleDTO {
    private String writer;
    private String title;
    private String contents;
    private LocalDateTime date;
    private int id;
    private int commentSize;
    private List<ArticleDTO> comments;
    private int parent;

    public ArticleDTO(Article article) {
        this.id = article.getId();
        this.writer = article.getWriter();
        this.title = article.getTitle();
        this.contents = article.getContents();
        this.date = article.getDate();
        this.parent = article.getParent();
    }
}
