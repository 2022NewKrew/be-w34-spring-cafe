package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleCreateRequest;
import com.kakao.cafe.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void createArticle(ArticleCreateRequest request) {
        articleRepository.save(ArticleCreateRequest.getArticleFromNoDbIndex(request));
    }

    public List<Article> list() {
        return articleRepository.findAll();
    }

    public Article detail(int id) {
        return articleRepository.findById(id).get();
    }

    public void update(int id, ArticleCreateRequest request) {
        Article article = articleRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다."));

        article.updateArticle(request.getTitle(), request.getContents());
        articleRepository.update(article);
    }

    public void delete(int id) {
        Article article = articleRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다."));

        articleRepository.delete(article);
    }
}
