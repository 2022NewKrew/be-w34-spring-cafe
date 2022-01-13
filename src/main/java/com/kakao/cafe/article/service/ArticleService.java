package com.kakao.cafe.article.service;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.repository.ArticleRepository;
import com.kakao.cafe.article.web.dto.ArticleSaveDto;
import com.kakao.cafe.article.web.dto.ArticleShowDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public void addArticle(ArticleSaveDto articleSaveDto) {
        Article article = Article.builder()
            .writer(articleSaveDto.getWriter())
            .title(articleSaveDto.getTitle())
            .contents(articleSaveDto.getContents())
            .build();
        articleRepository.save(article);
    }

    public List<ArticleShowDto> findAllArticle() {
        List<Article> articleList = articleRepository.findAll();
        return articleList.stream()
            .map(this::createArticleShowDto)
            .collect(Collectors.toList());
    }

    private ArticleShowDto createArticleShowDto(Article article) {
        return ArticleShowDto.builder()
            .index(article.getId())
            .writer(article.getWriter())
            .title(article.getTitle())
            .contents(article.getContents())
            .build();
    }

    public ArticleShowDto findArticle(Long id) {
        return createArticleShowDto(articleRepository.findById(id));
    }
}
