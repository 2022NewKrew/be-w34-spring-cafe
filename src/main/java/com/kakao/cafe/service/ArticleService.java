package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleRequestDTO;
import com.kakao.cafe.dto.ArticleResponseDTO;
import com.kakao.cafe.error.exception.ArticleNotFoundException;
import com.kakao.cafe.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Transactional
    public void create(ArticleRequestDTO articleRequestDto) {
        articleRepository.save(articleRequestDto);
    }

    @Transactional
    public void update(Long id, ArticleRequestDTO articleRequestDTO) {
        Article article = articleRepository.findById(id)
                        .orElseThrow(ArticleNotFoundException::new);
        articleRepository.update(id, articleRequestDTO);
    }

    @Transactional(readOnly = true)
    public ArticleResponseDTO read(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(ArticleNotFoundException::new);
        return mapper(article);
    }

    @Transactional(readOnly = true)
    public List<ArticleResponseDTO> readAll() {
        return articleRepository.findAll()
                .stream()
                .map(this::mapper)
                .collect(Collectors.toUnmodifiableList());
    }

    @Transactional
    public void delete(Long id) {
        articleRepository.findById(id).orElseThrow(ArticleNotFoundException::new);
        articleRepository.delete(id);
    }

    private ArticleResponseDTO mapper(Article article) {
        return ArticleResponseDTO.builder()
                .id(article.getId())
                .author(article.getAuthor())
                .title(article.getTitle())
                .content(article.getContent())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .build();
    }
}
