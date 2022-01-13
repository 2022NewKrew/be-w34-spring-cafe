package com.kakao.cafe.service;

import com.kakao.cafe.dto.ArticleRegistrationDto;
import com.kakao.cafe.entity.Article;
import com.kakao.cafe.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    @Qualifier("articleRepository")
    ArticleRepository articleRepository;

    @Override
    public void write(ArticleRegistrationDto articleDto) {
        articleRepository.createArticle(articleDto);
    }

    @Override
    public List<Article> getArticles() {
        return articleRepository.readArticles();
    }

    @Override
    public Article findByArticleId(Integer articleId) {
        return articleRepository.readArticle(articleId);
    }
}
