package com.kakao.cafe.model;

public class Article {
    private final String writer;
    private final String title;
    private final String contents;

    public Article(ArticleDTO articleDTO){
        this.writer = articleDTO.getWriter();
        this.title = articleDTO.getTitle();
        this.contents = articleDTO.getContents();
    }

    public ArticleDTO toArticleDTO(){
        return new ArticleDTO(writer, title, contents);
    }
}
