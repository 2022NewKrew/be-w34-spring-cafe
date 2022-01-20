package com.kakao.cafe.domain.article;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArticlePage {

    public static final int DEFAULT_PAGE_SIZE = 5;
    public static final int DEFAULT_DISPLAY_PAGE_SIZE = 5;
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
        return startPage() - 1 >= DEFAULT_FIRST_PAGE;
    }

    public boolean hasNext() {
        return endPage() + 1 <= totalPages();
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
        return currentPage - DEFAULT_PAGE_SIZE / 2;
    }

    public int endPage() {
        return currentPage + DEFAULT_PAGE_SIZE / 2;
    }

    public int offset() {
        return (currentPage - 1) * articlesPerPage;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public List<Page> getPages() {
        int minPageNum = Math.max(1, startPage());
        int maxPageNum = Math.min(endPage(), totalPages());
        return IntStream.rangeClosed(minPageNum, maxPageNum)
                .mapToObj(i -> new Page(i, i == currentPage))
                .collect(Collectors.toList());
    }

    public int getTotalArticleSize() {
        return totalArticleSize;
    }

    public int getDisplayPageNum() {
        return displayPageNum;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public static class Page {
        private final int pageNumber;
        private final boolean isCurrentPage;

        public Page(int pageNumber, boolean isCurrentPage) {
            this.pageNumber = pageNumber;
            this.isCurrentPage = isCurrentPage;
        }
    }
}
