package com.kakao.cafe.article.service;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.QuestionDTO;
import com.kakao.cafe.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public void question(QuestionDTO questionDTO) {
        articleRepository.save(questionDTO);
    }

    public Article findById(Long id) {
        return articleRepository.findById(id).orElseThrow(()
                -> new RuntimeException("해당 id의 질문은 존재하지 않습니다."));
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }
}
