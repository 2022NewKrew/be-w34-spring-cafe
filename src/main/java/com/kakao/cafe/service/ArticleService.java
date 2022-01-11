package com.kakao.cafe.service;

import com.kakao.cafe.model.Article;
import com.kakao.cafe.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository repository;

    @Autowired
    public ArticleService(ArticleRepository repository) {
        this.repository = repository;
    }


    public void save(String title, String content){
        repository.save(title, content);
    }

    public Article findArticleById(String id){
        int index = Integer.parseInt(id.trim());
        return Optional.ofNullable(repository.findArticleById(index)).orElseThrow(IllegalArgumentException::new);
    }

    public List<Article> findAllArticles(){
        return repository.findAllArticles();
    }
}
