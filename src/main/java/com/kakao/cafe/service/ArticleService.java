package com.kakao.cafe.service;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.repository.RepositoryInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArticleService {
    private final RepositoryInterface<Article> articleRepository;

    public ArticleService(RepositoryInterface<Article> articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void join(Article article){
        articleRepository.save(article);
    }

    public List<Article> findArticles(){
        return articleRepository.findAll();
    }

    public List<Article> findSubList(int page){
        return new ArrayList<>(articleRepository.findAll().subList((page - 1) * 10, Math.min(numOfArticles(),page * 10)));
    }

    public Article findOne(Long index){
        Optional<Article> article = articleRepository.findById(index);
        if(article.isPresent()){
            return article.get();
        }
        throw new IllegalStateException("not valid index");
    }

    public int numOfArticles() {
        return articleRepository.findAll()
                .size();
    }
}
