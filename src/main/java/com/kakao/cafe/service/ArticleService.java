package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleRequestDTO;
import com.kakao.cafe.dto.ArticleResponseDTO;
import com.kakao.cafe.error.exception.ArticleNotFoundException;
import com.kakao.cafe.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public void create(ArticleRequestDTO articleRequestDto) {
        articleRepository.save(articleRequestDto);
    }

    public ArticleResponseDTO read(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(ArticleNotFoundException::new);
        return ArticleResponseDTO.of(article.getId(), article.getAuthor(), article.getTitle(), article.getContent(), article.getCreatedAt());
    }

    public List<ArticleResponseDTO> readAll() {
        return articleRepository.findAll()
                .stream()
                .map(article -> ArticleResponseDTO.of(article.getId(), article.getAuthor(), article.getTitle(), article.getContent(), article.getCreatedAt()))
                .collect(Collectors.toUnmodifiableList());
    }
}
