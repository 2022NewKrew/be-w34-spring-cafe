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
}
