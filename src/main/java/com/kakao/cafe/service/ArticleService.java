package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.dtos.ArticleResponseDto;
import com.kakao.cafe.domain.dtos.ArticleSaveDto;
import com.kakao.cafe.exception.NotFoundException;
import com.kakao.cafe.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
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

    public ArticleResponseDto findById(long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 아이디의 게시물이 없습니다."));
        return new ArticleResponseDto(article);
    }
}
