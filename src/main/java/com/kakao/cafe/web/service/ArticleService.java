package com.kakao.cafe.web.service;

import com.kakao.cafe.web.domain.Article;
import com.kakao.cafe.web.dto.ArticleDTO;
import com.kakao.cafe.web.repository.ArticleRepository;
import com.kakao.cafe.web.repository.MemoryArticleRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ArticleService {
    private final ArticleRepository memoryArticleRepository;

    public ArticleService(JdbcTemplate jdbcTemplate) {
        this.memoryArticleRepository = new MemoryArticleRepository(jdbcTemplate);
    }

    public Article writeArticle(ArticleDTO articleDTO) {
        return memoryArticleRepository.save(articleDTO);
    }

    public List<Article> getArticleList() {
        return memoryArticleRepository.getArticleList();
    }

    public Article getArticleById(long id) {
        return memoryArticleRepository.getArticleById(id);
    }
}
