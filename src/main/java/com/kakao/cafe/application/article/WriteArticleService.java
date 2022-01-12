package com.kakao.cafe.application.article;

import com.kakao.cafe.domain.article.ArticleVo;
import com.kakao.cafe.domain.article.WriteArticlePort;

public class WriteArticleService {

    private final WriteArticlePort writeArticlePort;

    public WriteArticleService(WriteArticlePort writeArticlePort) {
        this.writeArticlePort = writeArticlePort;
    }

    public void write(ArticleVo articleVo) {
        writeArticlePort.save(articleVo);
    }

}
