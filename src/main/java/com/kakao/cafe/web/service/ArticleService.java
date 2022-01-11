package com.kakao.cafe.web.service;

import com.kakao.cafe.web.domain.Article;
import com.kakao.cafe.web.dto.ArticleDTO;
import com.kakao.cafe.web.repository.ArticleRepository;
import com.kakao.cafe.web.repository.MemoryArticleRepository;

import java.util.List;

public class ArticleService {
    private final ArticleRepository memoryArticleRepository;

    public ArticleService() {
        this.memoryArticleRepository = new MemoryArticleRepository();
    }

    public Article writeArticle(ArticleDTO articleDTO) {
        return memoryArticleRepository.save(articleDTO);
    }

    public List<Article> getArticleList() {
        return memoryArticleRepository.getArticleList();
    }

    public Article getArticleById(int id) {
        return memoryArticleRepository.getArticleById(id);
    }
}
