package com.kakao.cafe.controller.dto;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.util.ValidInfo;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class ArticleSaveForm {
    private String writer;
    @Size(max= ValidInfo.MAX_ARTICLE_TITLE_LEN, message = ValidInfo.ARTICLE_TITLE_MESSAGE)
    private String title;
    @Size(max= ValidInfo.MAX_ARTICLE_CONTENT_LEN, message = ValidInfo.ARTICLE_CONTENT_MESSAGE)
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
