package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleRequestDto;
import com.kakao.cafe.dto.ArticleResponseDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MemoryArticleRepository implements ArticleRepository{
    private final List<Article> articles = new ArrayList<>();
    private static long sequence = 0L;
    private final Logger logger = LoggerFactory.getLogger(MemoryArticleRepository.class);

    @Override
    public void save(ArticleRequestDto articleRequestDto) {
        Article article = Article.of(++sequence, articleRequestDto.getAuthor(), articleRequestDto.getTitle(), articleRequestDto.getContent(), LocalDateTime.now());
        logger.info("save: {}, {}", article.getId(), article.getAuthor());
        articles.add(article);
    }

    @Override
    public Optional<ArticleResponseDto> findById(Long id) {
        return articles.stream()
                .filter(article -> article.getId().equals(id))
                .map(article -> ArticleResponseDto.of(article.getId(), article.getAuthor(), article.getTitle(), article.getContent(), article.getCreatedAt()))
                .findFirst();
    }

    @Override
    public List<ArticleResponseDto> findAll() {
        return articles.stream()
                .map(article -> ArticleResponseDto.of(article.getId(), article.getAuthor(), article.getTitle(), article.getContent(), article.getCreatedAt()))
                .collect(Collectors.toUnmodifiableList());
    }
}
