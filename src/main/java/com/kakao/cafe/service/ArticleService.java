package com.kakao.cafe.service;

import com.kakao.cafe.controller.dto.ArticleResponseDto;
import com.kakao.cafe.controller.dto.ArticleDto;
import com.kakao.cafe.domain.Article;
import com.kakao.cafe.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Long createPost(ArticleDto articleDto) {
        Article article = Article.from(articleDto);
        articleRepository.save(article);
        return article.getArticleId();
    }

    public List<ArticleResponseDto> findAll() {
        return articleRepository.findAll().stream().map(ArticleResponseDto::from).collect(Collectors.toList());
    }

    public ArticleResponseDto findById(Long id) {
        Article byId = articleRepository.findById(id);
        return ArticleResponseDto.from(byId);
    }
}
