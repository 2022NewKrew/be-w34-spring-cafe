package com.kakao.cafe.application;

import com.kakao.cafe.domain.article.ArticlePort;
import com.kakao.cafe.domain.article.ArticleVo;

public class ArticleService {

    private final ArticlePort articlePort;

    public ArticleService(ArticlePort articlePort) {
        this.articlePort = articlePort;
    }

    public void write(ArticleVo articleVo) {
        articlePort.save(articleVo);
    }
}
