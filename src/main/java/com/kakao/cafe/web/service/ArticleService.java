package com.kakao.cafe.web.service;

import com.kakao.cafe.web.domain.Article;
import com.kakao.cafe.web.dto.ArticleDTO;
import com.kakao.cafe.web.repository.ArticleRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository memoryArticleRepository;

    public ArticleService(ArticleRepository memoryArticleRepository) {
        this.memoryArticleRepository = memoryArticleRepository;
    }

    public Article writeArticle(ArticleDTO articleDTO) {
        return memoryArticleRepository.save(articleDTO);
    }

    public List<Article> getArticleList() {
        return memoryArticleRepository.getArticleList();
    }

    public Article getArticleById(long id) {
        Optional<Article> article = memoryArticleRepository.getArticleById(id);
        return article.orElseThrow();
    }
}
