package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.article.dto.ArticleCreateRequestDto;
import com.kakao.cafe.domain.article.dto.ArticleResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Transactional
    public Long createArticle(ArticleCreateRequestDto requestDto) {
        return articleRepository.save(requestDto.toArticle());
    }

    @Transactional(readOnly = true)
    public ArticleResponseDto retrieveArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("글이 없습니다."));
        return new ArticleResponseDto(article);
    }

    @Transactional(readOnly = true)
    public List<ArticleResponseDto> retrieveAllArticles() {
        return articleRepository.findAll().stream().map(ArticleResponseDto::new).collect(Collectors.toList());
    }
}
