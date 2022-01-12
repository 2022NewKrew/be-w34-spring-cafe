package com.kakao.cafe.service;

import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.repository.ArticleRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void postArticle(ArticleDto article) {
        articleRepository.save(article);
    }

    public List<ArticleDto> getArticleList() {
        return articleRepository.getAllArticles();
    }

    public ArticleDto findByIndex(int index) {
        ArticleDto article = articleRepository.findByIndex(index);
        if (article == null)
            throw new NoSuchElementException("해당 Index를 가진 article이 존재하지 않음");
        return article;
    }

    public int nextArticleId() {
        return articleRepository.getAllArticles().size() + 1;
    }
}
