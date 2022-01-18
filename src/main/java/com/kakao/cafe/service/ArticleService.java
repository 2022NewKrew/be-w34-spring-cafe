package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.dtos.ArticleResponseDto;
import com.kakao.cafe.domain.dtos.ArticleSaveDto;
import com.kakao.cafe.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(@Qualifier("articleRepositoryJdbcImpl") ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void save(ArticleSaveDto dto) {
        Article newArticle = new Article(dto.getWriter(), dto.getTitle(), dto.getContent());
        articleRepository.save(newArticle);
    }

    public List<ArticleResponseDto> findAll() {
        return articleRepository.findAll().stream()
                .map(ArticleResponseDto::new)
                .collect(Collectors.toList());
    }

    public ArticleResponseDto findById(Long id) {
        Article article = articleRepository.findById(id);
        return new ArticleResponseDto(article);
    }

    public void update(Long id, ArticleSaveDto dto) {
        Article article = articleRepository.findById(id);
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        articleRepository.save(article);
    }

    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }
}
