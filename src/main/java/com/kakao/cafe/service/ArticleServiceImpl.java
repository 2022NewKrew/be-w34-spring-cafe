package com.kakao.cafe.service;

import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.vo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    @Qualifier("articleRepository")
    ArticleRepository articleRepository;

    @Override
    public void write(Article article) {
        articleRepository.createArticle(article);
    }

    @Override
    public List<Article> getArticles() {
        return articleRepository.readArticles();
    }

    @Override
    public Article findByArticleId(Integer articleId) {
        return articleRepository.readArticle(articleId);
    }
}
