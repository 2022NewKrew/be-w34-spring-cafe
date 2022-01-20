package com.kakao.cafe.domain;

import com.kakao.cafe.dto.SampleArticleForm;

import java.util.concurrent.atomic.AtomicInteger;

public class Article {

    private static AtomicInteger currentIdx = new AtomicInteger();
    private Integer articleID;
    private String title;
    private String content;

    private Article(String title, String content) {
        this.articleID = currentIdx.get();
        currentIdx.set(currentIdx.get() + 1);
        this.title = title;
        this.content = content;
    }

    public Integer getArticleID() {
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
