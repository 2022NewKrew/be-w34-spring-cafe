package com.kakao.cafe.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired(required = false)
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

}
