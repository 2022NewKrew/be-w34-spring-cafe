package com.kakao.cafe.service.validation;

import com.kakao.cafe.exception.article.ArticleNotFoundException;
import com.kakao.cafe.model.vo.ArticleVo;
import org.springframework.stereotype.Component;

@Component
public class ArticleValidation {

    public void validateArticle(ArticleVo article) {
        if (article == null) {
            throw new ArticleNotFoundException();
        }
    }
}
