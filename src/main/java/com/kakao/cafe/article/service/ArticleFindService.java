package com.kakao.cafe.article.service;

import com.kakao.cafe.article.dto.ArticleDto;
import com.kakao.cafe.article.mapper.ArticleMapper;
import com.kakao.cafe.article.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleFindService {
    ArticleRepository articleRepository;
    ArticleMapper articleMapper;

    public ArticleFindService(ArticleRepository articleRepository, ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }

    public List<ArticleDto> getArticleList() {
        return articleMapper.toDtoList(articleRepository.findAll());
    }

    public ArticleDto getArticle(Long id) {
        return articleMapper.toDto(articleRepository.findById(id));
    }
}
