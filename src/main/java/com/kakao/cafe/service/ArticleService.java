package com.kakao.cafe.service;

import com.kakao.cafe.model.Article;
import com.kakao.cafe.model.ArticleSaveDTO;
import com.kakao.cafe.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository repository;

    public void save(ArticleSaveDTO articleSaveDTO){
        repository.save(articleSaveDTO);
    }

    public Article findArticleById(String id){
        int index = Integer.parseInt(id.trim());
        return Optional.ofNullable(repository.findArticleById(index)).orElseThrow(IllegalArgumentException::new);
    }

    public List<Article> findAllArticles(){
        return repository.findAllArticles();
    }
}
