package com.kakao.cafe.repository;

import com.kakao.cafe.model.Article;
import com.kakao.cafe.model.ArticleSaveDTO;
import com.kakao.cafe.model.Articles;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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
