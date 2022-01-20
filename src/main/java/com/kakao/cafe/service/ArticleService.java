package com.kakao.cafe.service;

import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.model.Article;
import com.kakao.cafe.repository.ArticleJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleJdbcRepository articleRepository;

    @Autowired
    public ArticleService(ArticleJdbcRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void save(ArticleDto articleDto) {
        articleRepository.save(articleDto.toEntity());
    }

    public List<ArticleDto> findAll() {
        return articleRepository.findAll()
                .stream()
                .map(Article::toDto)
                .collect(Collectors.toList());
    }

    public ArticleDto findOne(Integer id) {
        return articleRepository.findOne(id).orElseThrow().toDto();
    }

    public void update(ArticleDto articleDto) {
        articleRepository.update(articleDto.toEntity());
    }

    public void delete(Integer id) {
        articleRepository.delete(id);
    }

}
