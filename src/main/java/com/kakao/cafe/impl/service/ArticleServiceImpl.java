package com.kakao.cafe.impl.service;

import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.vo.Article;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    @Resource(name = "articleRepository")
    ArticleRepository articleRepository;

    @Override
    public long insertArticle(Article article) {
        return articleRepository.insertArticle(article);
    }

    @Override
    public List<Article> getArticleList() {
        return articleRepository.getAllArticle();
    }

    @Override
    public Article getArticleById(long articleId) {
        return articleRepository.getArticleById(articleId);
    }
}
