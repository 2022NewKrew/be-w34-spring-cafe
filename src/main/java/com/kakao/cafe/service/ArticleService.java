package com.kakao.cafe.service;

import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.repository.ArticleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void saveArticle(ArticleDto article) {
        articleRepository.save(article);
    }

    public List<ArticleDto> getArticleList() {
        return articleRepository.getAllArticles();
    }

    public ArticleDto getArticleBy(int index) {
        return articleRepository.getByIndex(index);
    }
}
