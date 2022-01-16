package com.kakao.cafe.repository;

import com.kakao.cafe.domain.model.Article;
import com.kakao.cafe.domain.dto.ArticleSaveDTO;
import com.kakao.cafe.domain.model.Articles;
import org.springframework.stereotype.Repository;

import java.util.List;

public class ArticleMemoryRepository implements ArticleRepository {
    private final Articles articles = new Articles();

    @Override
    public void save(ArticleSaveDTO articleSaveDTO) {
        articles.addArticle(articleSaveDTO);
    }

    @Override
    public Article findArticleById(int id) {
        return articles.findArticleById(id);
    }

    @Override
    public List<Article> findAllArticles() {
        return articles.getAllArticles();
    }
}
