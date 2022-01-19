package com.kakao.cafe.article.service;

import com.kakao.cafe.article.dto.ArticleDto;
import com.kakao.cafe.article.mapper.ArticleMapper;
import com.kakao.cafe.article.repository.ArticleRepository;
import org.springframework.stereotype.Service;

@Service
public class ArticleCreateService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    public ArticleCreateService(ArticleRepository articleRepository, ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }

    public void save(ArticleDto articleDto) {
        articleRepository.save(articleMapper.toEntity(articleDto));
    }
}
