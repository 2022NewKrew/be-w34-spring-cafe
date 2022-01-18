package com.kakao.cafe.application.article;

import com.kakao.cafe.domain.article.ArticleDaoPort;
import com.kakao.cafe.domain.article.ArticleVo;

public class WriteArticleService {
    private final ArticleDaoPort articleDao;

    public WriteArticleService(ArticleDaoPort articleDao) {
        this.articleDao = articleDao;
    }

    public void write(ArticleVo articleVo) {
        articleDao.save(articleVo);
    }
}
