package com.kakao.cafe.service;


import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.SampleArticleForm;
import com.kakao.cafe.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;

public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void addArticle(String author, SampleArticleForm form){
        Article article = Article.add(author, form);
        articleRepository.save(article);
    }

    public Article findArticle(Long articleID){
        return articleRepository.findByID(articleID);
    }

    public List<Article> getArticles(){
        return articleRepository.findAll();
    }
}
