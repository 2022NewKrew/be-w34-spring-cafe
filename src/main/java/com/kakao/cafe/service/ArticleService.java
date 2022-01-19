package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.ArticleRepository;
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
        final Integer aid = articleRepository.articlesSize() + 1;
        final Article article = new Article(aid, title, writer, contents);
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
