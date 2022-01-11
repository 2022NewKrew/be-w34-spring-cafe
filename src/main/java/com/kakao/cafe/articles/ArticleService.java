package com.kakao.cafe.articles;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(MemoryArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleDto createArticle(ArticleRequest articleRequest) {
        int articleId = articleRepository.getSize() + 1;

        ArticleContent content = new ArticleContent(articleRequest.getContent());
        Article article = new Article((long) articleId, articleRequest.getTitle(), content, articleRequest.getWriter());

        Article savedArticle = articleRepository.save(article);

        return ArticleDto.toDto(savedArticle);
    }

    public ArticleDto getArticleById(Long id) {
        Article article = articleRepository.findById(id).get();

        return ArticleDto.toDto(article);
    }

    public List<ArticleDto> getArticles() {
        return articleRepository.findAll().stream().map(ArticleDto::toDto).collect(Collectors.toList());
    }
}
