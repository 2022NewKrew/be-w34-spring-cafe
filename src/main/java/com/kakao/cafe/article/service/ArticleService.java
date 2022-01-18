package com.kakao.cafe.article.service;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.repository.ArticleRepository;
import com.kakao.cafe.user.domain.User;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article createArticle(String title, User writer, String contents) {
        final LocalDateTime now = LocalDateTime.now();
        final Integer aid = articleRepository.articlesSize() + 1;
        final Article article = new Article(aid, title, writer, contents, now, now);
        articleRepository.createArticle(article);
        return article;
    }

    public List<Article> getArticles() {
        return articleRepository.readArticleList();
    }

    public Article getArticleById(Integer aid) {
        if (!articleRepository.isIdUsed(aid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "[ERROR] 게시글을 찾을 수 없습니다.");
        }
        return articleRepository.readArticle(aid);
    }
}
