package com.kakao.cafe.domain.article.dto;

import com.kakao.cafe.domain.article.Article;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleUpdateForm {
    private Long id;
    private String title;
    private String content;

    public static ArticleUpdateForm from(Article article) {
        ArticleUpdateForm updateForm = new ArticleUpdateForm();
        updateForm.setContent(article.getContent());
        updateForm.setTitle(article.getTitle());
        updateForm.setId(article.getArticleId());
        return updateForm;
    }
}
