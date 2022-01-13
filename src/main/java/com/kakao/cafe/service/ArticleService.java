package com.kakao.cafe.service;

import com.kakao.cafe.controller.ArticleDto;
import com.kakao.cafe.domain.Article;
import com.kakao.cafe.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<ArticleDto> findAll() {
        return articleRepository
                .findAll()
                .stream()
                .map(ArticleDto::from)
                .collect(Collectors.toList());
    }

    public ArticleDto findById(int id) {
        return ArticleDto.from(articleRepository.findById(id).get());
    }

    public int create(ArticleDto articleDto) {
        Article article = articleDto.toEntity();
        return articleRepository.save(article);
    }
}
