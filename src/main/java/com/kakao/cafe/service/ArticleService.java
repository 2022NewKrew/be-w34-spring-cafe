package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleRequestDTO;
import com.kakao.cafe.dto.ArticleResponseDTO;
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

    public Optional<ArticleResponseDTO> read(Long id) {
        Optional<Article> articleOptional = articleRepository.findById(id);
        if(articleOptional.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 게시글입니다.");
        }
        Article article = articleOptional.get();
        return Optional.ofNullable(ArticleResponseDTO.of(article.getId(), article.getAuthor(), article.getTitle(), article.getContent(), article.getCreatedAt()));
    }

    public List<ArticleResponseDTO> readAll() {
        return articleRepository.findAll()
                .stream()
                .map(article -> ArticleResponseDTO.of(article.getId(), article.getAuthor(), article.getTitle(), article.getContent(), article.getCreatedAt()))
                .collect(Collectors.toUnmodifiableList());
    }
}
