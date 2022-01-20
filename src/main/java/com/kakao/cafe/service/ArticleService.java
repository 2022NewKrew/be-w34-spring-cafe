package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleDTO;
import com.kakao.cafe.dto.ArticlesDTO;
import com.kakao.cafe.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void save(ArticleDTO articleDTO) {
        // DTO to Entity
        Article article = ArticleDTO.toEntity(articleDTO);
        articleRepository.save(article);
    }

    public ArticlesDTO findAll() {
        List<Article> articles = articleRepository.findAll();

        if (articles.isEmpty()) {
            return null;
        }

        return new ArticlesDTO(articles);
    }

    public ArticleDTO findById(Integer index) {
        Article articleInfo = articleRepository.findByIndex(index);
        // Entity to DTO
        return ArticleDTO.toDto(articleInfo);
    }

}
