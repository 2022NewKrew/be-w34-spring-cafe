package com.kakao.cafe.application.article;

import com.kakao.cafe.domain.article.ArticleVo;
import com.kakao.cafe.domain.article.UpdateArticlePort;

public class UpdateArticleService {

    private final UpdateArticlePort updateArticlePort;

    public UpdateArticleService(UpdateArticlePort updateArticlePort) {
        this.updateArticlePort = updateArticlePort;
    }

    public void update(ArticleVo articleVo) {updateArticlePort.update(articleVo);
    }
}
