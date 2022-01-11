package com.kakao.cafe.service;

import com.kakao.cafe.controller.articles.dto.ArticleDetailDto;
import com.kakao.cafe.controller.articles.dto.ArticleItemDto;
import com.kakao.cafe.controller.articles.mapper.ArticleItemDtoMapper;
import com.kakao.cafe.domain.Article;
import com.kakao.cafe.repository.article.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private ArticleRepository articleRepository;

    @Autowired
    public ArticleService(
            @Qualifier("memoryArticleRepository") ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<ArticleItemDto> getArticleAll() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream()
                .map(ArticleItemDtoMapper::toArticleItemDto)
                .collect(Collectors.toList());
    }

    public ArticleDetailDto getArticleDetail(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow();
        return ArticleItemDtoMapper.toArticleDetailDto(article);
    }

    public Long writeArticle(String writer, String title, String contents) {
        Article article = Article.of(writer, title, contents);
        articleRepository.insertArticle(article);
        return article.getId();
    }
}
