package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.mapper.ArticleMapper;
import com.kakao.cafe.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }


    public void save(ArticleDto articleDto) {
        articleRepository.save(articleMapper.toEntity(articleDto));
    }

    public Article findArticle(Long id) {
        return articleRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("해당 id에 맞는 article이 존재하지 않습니다."));
    }

    public List<Article> findArticleList() {
        return articleRepository.findAll();
    }
}
