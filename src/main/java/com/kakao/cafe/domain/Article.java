package com.kakao.cafe.domain;

import com.kakao.cafe.dto.SampleArticleForm;


public class Article {

    private Long articleID;
    private String title;
    private String content;

    private Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setArticleID(Long articleID) {
        this.articleID = articleID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getArticleID() {
        return articleID;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public static Article add(SampleArticleForm form){
        return new Article(form.getTitle(), form.getContent());
    }
}
