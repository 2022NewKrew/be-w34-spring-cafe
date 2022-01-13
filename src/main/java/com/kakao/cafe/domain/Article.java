package com.kakao.cafe.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * author    : brody.moon
 * version   : 1.0
 * Article 정보 클래스입니다.
 */
public class Article {
    private final String writer;
    private final String title;
    private final String contents;
    private final LocalDateTime date;
    private final List<Article> comments;

    public Article(ArticleDTO articleDTO){
        this.writer = articleDTO.getWriter();
        this.title = articleDTO.getTitle();
        this.contents = articleDTO.getContents();
        this.date = articleDTO.getDate();
        this.comments = new ArrayList<>();
    }

    public int commentSize(){
        return comments.size();
    }

    /**
     * Article 클래스를 DTO 클래스로 매핑해주는 메서드입니다.
     * @return  DTO 클래스
     */
    public ArticleDTO toArticleDTO(){
        return new ArticleDTO(writer, title, contents);
    }

    public List<Article> getComments() {
        return comments;
    }
}
