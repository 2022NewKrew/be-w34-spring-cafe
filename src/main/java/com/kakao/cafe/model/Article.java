package com.kakao.cafe.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Article {
    private final String writer;
    private final String title;
    private final String contents;
    private final LocalDateTime date;
    private final List<ArticleComment> comments;

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
    public ArticleDTO toArticleDTO(){
        return new ArticleDTO(writer, title, contents);
    }
}
