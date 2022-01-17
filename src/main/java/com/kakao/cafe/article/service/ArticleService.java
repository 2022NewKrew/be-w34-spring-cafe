package com.kakao.cafe.article.service;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.ArticleRequestDTO;
import com.kakao.cafe.article.dto.ArticleResponseDTO;
import com.kakao.cafe.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Long posting(ArticleRequestDTO articleRequestDTO) {
        Article article = articleRequestDTO.toArticle(LocalDate.now());
        return articleRepository.save(article).getId();
    }

    public ArticleResponseDTO findOne(Long id) {
        return new ArticleResponseDTO(articleRepository.findOne(id).orElseThrow(() -> {
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
        }));
    }

    public List<ArticleResponseDTO> findAll() {
        return articleRepository.findAll().stream()
                .map(ArticleResponseDTO::new)
                .collect(Collectors.toList());
    }

    public void update(Long id) {
        // articleRepository.update(id);
    }
}
