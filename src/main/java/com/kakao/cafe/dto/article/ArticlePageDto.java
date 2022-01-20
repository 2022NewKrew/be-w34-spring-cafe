package com.kakao.cafe.dto.article;

import com.kakao.cafe.domain.article.ArticlePage;

import java.util.List;
import java.util.stream.Collectors;

public class ArticlePageDto {

    private final List<ArticleDto> articles;
    private final int totalArticleSize;
    private final int currentPage;
    private final boolean hasPrev;
    private final boolean hasNext;
    private final int prevPageNum;
    private final int nextPageNum;

    public ArticlePageDto(ArticlePage articlePage) {
        this.articles = articlePage.getArticles().stream()
                .map(ArticleDto::new)
                .collect(Collectors.toList());
        this.totalArticleSize = articlePage.getTotalArticleSize();
        this.currentPage = articlePage.getCurrentPage();
        this.hasPrev = articlePage.hasPrev();
        this.hasNext = articlePage.hasNext();
        this.prevPageNum = articlePage.getPrevPageNum();
        this.nextPageNum = articlePage.getNextPageNum();
    }
}
