package com.kakao.cafe.service;

import com.kakao.cafe.dto.ArticleRegistrationDto;
import com.kakao.cafe.entity.Article;
import com.kakao.cafe.repository.JdbcArticleRepository;

import java.util.List;
import java.util.Optional;

public class ArticleServiceImpl implements ArticleService{

    private final JdbcArticleRepository jdbcArticleRepository;

    public ArticleServiceImpl(JdbcArticleRepository jdbcArticleRepository) {
        this.jdbcArticleRepository = jdbcArticleRepository;
    }

    @Override
    public void write(ArticleRegistrationDto articleDto) {
        jdbcArticleRepository.create(articleDto.toEntity());}

    @Override
    public List<Article> getArticles() {
        return jdbcArticleRepository.readAll();
    }

    @Override
    public Article findByArticleId(Integer articleId) {
        Optional<Article> article = jdbcArticleRepository.readById(articleId);
        return article.orElseThrow(() -> new RuntimeException("article 조회 null 검증"));
    }
}
