package com.kakao.cafe.article.service;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.repository.ArticleRepository;
import com.kakao.cafe.user.domain.User;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article createArticle(String title, User writer, String contents) {
        final LocalDateTime now = LocalDateTime.now();
        final Integer aid = articleRepository.articlesSize();
        final Article article = new Article(aid, title, writer, contents, now, now);
        articleRepository.createArticle(article);
        return article;
    }

    public List<Article> getArticles() {
        return articleRepository.readArticleList();
    }
}
