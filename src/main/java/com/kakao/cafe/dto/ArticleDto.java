package com.kakao.cafe.dto;

import java.util.LinkedList;
import java.util.List;

import com.kakao.cafe.domain.Article;

public class ArticleDto {
    private String title;
    private String content;
    private String writer;
    private Integer articleIndex;

    private List<ReplyDto> replyDtoList;

    public ArticleDto(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.replyDtoList = new LinkedList<>();
    }

    public ArticleDto(String title, String content, String writer, Integer articleIndex) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.articleIndex = articleIndex;
        this.replyDtoList = new LinkedList<>();
    }

    public ArticleDto(String title, String content, String writer, Integer articleIndex, List<ReplyDto> replyDtoList) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.articleIndex = articleIndex;
        this.replyDtoList = replyDtoList;
    }

    public ArticleDto(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
        this.articleIndex = article.getArticleIndex();
        this.writer = article.getWriter();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getArticleIndex() {
        return this.articleIndex;
    }

    public void setArticleIndex(Integer articleIndex) {
        this.articleIndex = articleIndex;
    }

    public List<ReplyDto> getReplyDTOList() {
        return replyDtoList;
    }

    public void setReplyDTOList(List<ReplyDto> replyDtoList) {
        this.replyDtoList = replyDtoList;
    }

    public Article toArticle() {
        return new Article(title, content, writer, articleIndex);
    }

}
