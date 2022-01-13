package com.kakao.cafe.controller.dto;

import com.kakao.cafe.domain.Article;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleSaveForm {
    private String writer;
    private String title;
    private String content;

    public ArticleSaveForm() {}

    public static ArticleSaveForm from(Article article) {
        ArticleSaveForm articleSaveForm = new ArticleSaveForm();
        articleSaveForm.setTitle(article.getTitle());
        articleSaveForm.setWriter(article.getWriter());
        articleSaveForm.setContent(article.getContent());
        return articleSaveForm;
    }
}
