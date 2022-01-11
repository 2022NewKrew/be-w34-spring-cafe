package com.kakao.cafe.service;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.RepositoryInterface;

import java.util.List;
import java.util.Optional;

public class ArticleService {
    private final RepositoryInterface<com.kakao.cafe.domain.article.Article> articleRepository = new ArticleRepository();

    public void join(Article article){
        articleRepository.save(article);
    }

    public List<Article> findArticles(){
        return articleRepository.findAll();
    }

    public Optional<Article> findOne(Long index){
        return articleRepository.findById(index);
    }
}
