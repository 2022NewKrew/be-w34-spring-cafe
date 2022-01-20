package com.kakao.cafe.domain.article;

import java.util.List;

public class ArticlePage {

    public static final int DEFAULT_PAGE_SIZE = 15;
    public static final int DEFAULT_DISPLAY_PAGE_SIZE = 10;
    public static final int DEFAULT_FIRST_PAGE = 1;

    private List<Article> articles;
    private final int totalArticleSize;
    private final int currentPage;
    private final int articlesPerPage;
    private final int displayPageNum;

    public ArticlePage(int totalArticleSize, int currentPage) {
        this.articles = null;
        this.totalArticleSize = totalArticleSize;
        this.currentPage = Math.max(DEFAULT_FIRST_PAGE, currentPage);
        this.articlesPerPage = DEFAULT_PAGE_SIZE;
        this.displayPageNum = DEFAULT_DISPLAY_PAGE_SIZE;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public boolean hasPrev() {
        return startPage() != DEFAULT_FIRST_PAGE;
    }

    public boolean hasNext() {
        return endPage() != totalPages();
    }

    public int getPrevPageNum() {
        return Math.max(startPage() - 1, DEFAULT_FIRST_PAGE);
    }

    public int getNextPageNum() {
        return Math.min(endPage() + 1, totalPages());
    }

    public int totalPages() {
        return (totalArticleSize - 1) / articlesPerPage + 1;
    }

    public int startPage() {
        return (currentPage - 1) / displayPageNum * displayPageNum + 1;
    }

    public int endPage() {
        int endPage = ((currentPage - 1) / displayPageNum + 1) * displayPageNum;
        return Math.min(totalPages(), endPage);
    }

    public int offset() {
        return (currentPage - 1) * articlesPerPage;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public int getTotalArticleSize() {
        return totalArticleSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }
}
