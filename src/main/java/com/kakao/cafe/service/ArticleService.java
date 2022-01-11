package com.kakao.cafe.service;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticleDto;
import com.kakao.cafe.domain.article.ArticleRequest;
import com.kakao.cafe.exception.EntityNotFoundException;
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
        Article article = new Article(articleRequest);
        articleRepository.save(article);
    }

    public List<ArticleDto> getArticles() {
        return articleRepository.findAll().stream()
                .map(Article::toDto)
                .collect(Collectors.toList());
    }

    public ArticleDto findById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 게시글입니다."))
                .toDto();
   }
}
