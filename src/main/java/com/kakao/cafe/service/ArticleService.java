package com.kakao.cafe.service;

import com.kakao.cafe.controller.dto.ArticleResponse;
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

    public List<ArticleResponse> findAll() {
        return articleRepository.findAll().stream().map(ArticleResponse::from).collect(Collectors.toList());
    }

    public ArticleResponse findById(Long id) {
        Article byId = articleRepository.findById(id);
        return ArticleResponse.from(byId);
    }
}
