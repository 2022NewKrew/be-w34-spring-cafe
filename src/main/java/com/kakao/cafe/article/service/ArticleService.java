package com.kakao.cafe.article.service;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.ArticleViewDTO;
import com.kakao.cafe.article.dto.QuestionDTO;
import com.kakao.cafe.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Article question(QuestionDTO questionDTO) {
        return articleRepository.save(new Article(questionDTO));
    }

    public List<ArticleViewDTO> getAllArticles() {
        return articleRepository.findAll().stream()
                .map(article -> new ArticleViewDTO(article))
                .collect(Collectors.toList());
    }
}
