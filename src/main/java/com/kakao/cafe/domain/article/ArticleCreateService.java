package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.article.dao.ArticleRepository;
import com.kakao.cafe.domain.article.dto.ArticleCreateDto;
import org.springframework.stereotype.Service;

@Service
public class ArticleCreateService {

    private final ArticleRepository articleRepository;

    public ArticleCreateService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void createArticle(ArticleCreateDto dto) {
        articleRepository.save(dto.toEntity());
    }
}
