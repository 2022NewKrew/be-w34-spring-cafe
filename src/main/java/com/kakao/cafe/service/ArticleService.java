package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.ArticleDTO;
import com.kakao.cafe.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ArticleService implements Service<Article, ArticleDTO, Integer>{
    private final Repository<Article, ArticleDTO, Integer> articleRepository;

    @Autowired
    public ArticleService(Repository<Article, ArticleDTO, Integer> articleRepository){
        this.articleRepository = articleRepository;
    }

    @Override
    public Integer join(ArticleDTO articleDTO) {
        articleRepository.save(articleDTO);
        return articleDTO.getId();
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public Optional<Article> findOne(Integer id) {
        return articleRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        articleRepository.delete(id);
    }
}
