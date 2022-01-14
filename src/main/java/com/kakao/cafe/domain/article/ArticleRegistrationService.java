package com.kakao.cafe.domain.article;

import com.kakao.cafe.infra.dao.ArticleCreateCommand;
import com.kakao.cafe.infra.repository.article.ArticleRepository;
import com.kakao.cafe.web.article.form.ArticleRegistrationForm;

import java.time.LocalDateTime;

public class ArticleRegistrationService {
    private final ArticleRepository articleRepository;

    public ArticleRegistrationService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void registerArticle(ArticleRegistrationForm articleRegistrationForm) {
        articleRepository.saveArticle(new ArticleCreateCommand(LocalDateTime.now(),
                articleRegistrationForm.getWriterName(),
                articleRegistrationForm.getTitle(),
                articleRegistrationForm.getContents(),
                0));
    }
}
