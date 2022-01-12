package com.kakao.cafe.service;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.dto.article.ArticleDto;
import com.kakao.cafe.dto.article.ArticleRequest;
import com.kakao.cafe.exception.ArticleNotFoundException;
import com.kakao.cafe.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void publishArticle(ArticleRequest articleRequest) {
        Article article = new Article(articleRequest.getTitle(), articleRequest.getDescription());
        articleRepository.save(article);
    }

    public List<ArticleDto> getArticles() {
        return articleRepository.findAll().stream()
                .map(ArticleDto::new)
                .collect(Collectors.toList());
    }

    public ArticleDto findById(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("존재하지 않는 게시글입니다."));
        return new ArticleDto(article);
    }
}
