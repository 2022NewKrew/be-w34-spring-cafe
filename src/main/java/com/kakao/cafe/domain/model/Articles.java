package com.kakao.cafe.domain.model;

import com.kakao.cafe.domain.dto.ArticleSaveDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Articles {
    private final List<Article> articles = new ArrayList<>();

    public void addArticle(ArticleSaveDTO articleSaveDTO){
        validateArticleSaveDTO(articleSaveDTO);

        Article article = new Article(articles.size()+1, articleSaveDTO.getTitle(), articleSaveDTO.getContent());
        articles.add(article);
    }

    public Article findArticleById(int id){
        validateId(id);
        return articles.get(id-1);
    }

    public List<Article> getAllArticles(){
        return Collections.unmodifiableList(articles);
    }

    private void validateId(int id){
        if (id < 1 || id > articles.size()) {
            throw new IllegalArgumentException();
        }
    }

    private void validateArticleSaveDTO(ArticleSaveDTO articleSaveDTO){
        if(Objects.isNull(articleSaveDTO)) throw new IllegalArgumentException();
    }
}
