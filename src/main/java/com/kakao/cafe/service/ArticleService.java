package com.kakao.cafe.service;

import com.kakao.cafe.domain.dto.ArticleModifyDto;
import com.kakao.cafe.domain.model.Article;
import com.kakao.cafe.domain.dto.ArticleSaveDto;
import com.kakao.cafe.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository repository;

    public void save(ArticleSaveDto articleSaveDTO){
        repository.save(articleSaveDTO);
    }

    public Article findArticleById(String id){
        return Optional.ofNullable(repository.findArticleById(formatId(id))).orElseThrow(IllegalArgumentException::new);
    }

    public List<Article> findAllArticles(){
        return repository.findAllArticles();
    }

    public void modifyArticle(ArticleModifyDto articleModifyDto){
        repository.modifyArticle(articleModifyDto);
    }

    public void deleteArticle(String id){
        repository.deleteArticle(formatId(id));
    }

    private int formatId(String id){
        return Integer.parseInt(id.trim());
    }
}
