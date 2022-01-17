package com.kakao.cafe.service;

import com.kakao.cafe.domain.dto.article.ArticleContents;
import com.kakao.cafe.domain.dto.article.ArticleCreateCommand;
import com.kakao.cafe.domain.dto.article.ArticleListShow;
import com.kakao.cafe.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void createArticle(ArticleCreateCommand acc) {
        articleRepository.store(acc);
    }

    public ArticleContents getArticle(Long id) {
        return articleRepository.retrieve(id);
    }

    public List<ArticleListShow> getAllArticles() {
        return articleRepository.toList();
    }
}
