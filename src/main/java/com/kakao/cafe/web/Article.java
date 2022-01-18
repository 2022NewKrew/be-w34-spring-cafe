package com.kakao.cafe.web;

import com.kakao.cafe.dto.SampleArticleForm;
import com.kakao.cafe.dto.SampleUserForm;

public class Article {

    private static Integer currentIdx = 0;
    private Integer articleID;
    private String title;
    private String content;

    private Article(String title, String content) {
        this.articleID = currentIdx;
        currentIdx = currentIdx + 1;
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
