package com.kakao.cafe.infrastructure.article;

import com.kakao.cafe.domain.article.ArticlePort;
import com.kakao.cafe.domain.article.ArticleVo;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleInMemoryAdaptor implements ArticlePort {
    @Override
    public void save(ArticleVo article) {

    }
}
