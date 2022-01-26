package com.kakao.cafe.service;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.web.dto.ArticleRequest;
import com.kakao.cafe.web.dto.ArticleResponse;
import com.kakao.cafe.web.dto.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void createArticle(ArticleRequest articleRequest) {
        articleRepository.create(
                articleFromArticleRequest(articleRequest)
        );
    }

    public int getArticleListSize() {
        return articleRepository.getArticleList().size();
    }

    public List<ArticleResponse> getArticleList() {
        return articleRepository.getArticleList().stream().map(
                this::articleResponseFromArticle).collect(Collectors.toList()
        );
    }

    public ArticleResponse getArticleByIndex(int id) {
        return this.articleResponseFromArticle(articleRepository.findById(id));
    }

    private ArticleResponse articleResponseFromArticle(Article article) {
        return ArticleResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .createUserId(article.getCreateUserId())
                .createDate(article.getCreateDate())
                .views(article.getViews())
                .build();
    }

    private Article articleFromArticleRequest(ArticleRequest articleRequest) {
        return Article.builder()
                .title(articleRequest.getTitle())
                .content(articleRequest.getContent())
                .createUserId(articleRequest.getCreateUserId())
                .createDate(articleRequest.getCreateDate())
                .views(articleRequest.getViews())
                .build();
    }
}
