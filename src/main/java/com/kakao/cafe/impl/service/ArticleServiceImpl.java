package com.kakao.cafe.impl.service;

import com.kakao.cafe.dto.ArticleDTO;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    @Resource(name = "articleRepository")
    ArticleRepository articleRepository;

    @Override
    public long insertArticle(ArticleDTO article) {
        return articleRepository.insertArticle(article);
    }

    @Override
    public List<ArticleDTO> getArticleList() {
        return articleRepository.getAllArticle();
    }

    @Override
    public ArticleDTO getArticleById(long articleId) {
        return articleRepository.getArticleById(articleId);
    }

    @Override
    public int increaseViews(long articleId) {
        return articleRepository.increaseViews(articleId);
    }
}
