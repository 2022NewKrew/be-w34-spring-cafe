package com.kakao.cafe.qna.article.repository;

import com.kakao.cafe.qna.article.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melodist
 * Date: 2022-01-11 011
 * Time: 오후 1:48
 */
@Repository
public class MemoryArticleRepository implements ArticleRepository{

    private List<Article> articles = new ArrayList<>();

    @Override
    public Article save(Article article) {
        article.setId(articles.size() + 1);
        articles.add(article);
        return article;
    }

    @Override
    public Article findArticleById(Integer id) {
        for (Article article : articles) {
            if (article.getId() == id) {
                return article;
            }
        }
        return null;
    }

    @Override
    public List<Article> findAll() {
        return articles;
    }
}
