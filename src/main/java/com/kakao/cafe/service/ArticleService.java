package com.kakao.cafe.service;

import com.kakao.cafe.dto.article.ArticleRegistrationForm;
import com.kakao.cafe.dto.article.ArticleShowForm;
import com.kakao.cafe.dto.article.ArticleShowFormWithContents;
import com.kakao.cafe.entity.Article;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.util.TimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void createArticle(ArticleRegistrationForm articleRegistrationForm) {
        articleRepository.saveArticle(new Article(articleRegistrationForm.getWriterName(),
                articleRegistrationForm.getTitle(),
                articleRegistrationForm.getContents(),
                0,
                LocalDateTime.now()));
    }

    public List<ArticleShowForm> getArticleList() {
        List<Article> articleList = articleRepository.findAll();
        return articleList.stream().map(this::buildArticleShowForm).collect(Collectors.toList());
    }

    private ArticleShowForm buildArticleShowForm(Article article) {
        String formattedDate = TimeFormatter.formatStandardDateTime(article.getCreatedTime());
        return new ArticleShowForm(
                article.getArticleId(),
                article.getWriterName(),
                article.getTitle(),
                article.getNumberOfReply(),
                formattedDate
        );
    }

    public ArticleShowFormWithContents getArticleById(Long articleId) {
        Optional<Article> optionalArticle = articleRepository.findArticleById(articleId);
        if (optionalArticle.isEmpty()) {
            throw new RuntimeException("질문글을 찾을 수 없습니다.");
        }
        return buildArticleShowFormWithContents(optionalArticle.get());
    }

    private ArticleShowFormWithContents buildArticleShowFormWithContents(Article article) {
        String formattedDate = TimeFormatter.formatStandardDateTime(article.getCreatedTime());
        return new ArticleShowFormWithContents(
                article.getArticleId(),
                article.getWriterName(),
                article.getTitle(),
                article.getNumberOfReply(),
                formattedDate,
                article.getContents()
        );
    }
}
