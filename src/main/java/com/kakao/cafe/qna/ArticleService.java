package com.kakao.cafe.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by melodist
 * Date: 2022-01-11 011
 * Time: 오후 1:48
 */
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article saveArticle(Article article) {
        articleRepository.save(article);
        return article;
    }

    public Article findArticleById(Integer id) {
        return articleRepository.findArticleById(id);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }
}
