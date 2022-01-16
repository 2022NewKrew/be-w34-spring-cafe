package com.kakao.cafe.service;

import com.kakao.cafe.controller.ArticleDto;
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

    public List<ArticleDto> findAll() {
        return articleRepository
                .findAll()
                .stream()
                .map(ArticleDto::from)
                .collect(Collectors.toList());
    }

    public ArticleDto findById(int id) {
        return ArticleDto.from(articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물")));
    }

    public int create(ArticleDto articleDto) {
        Article article = articleDto.toEntity();
        return articleRepository.save(article);
    }

}
