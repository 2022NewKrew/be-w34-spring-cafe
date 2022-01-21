package com.kakao.cafe.domain;

import com.kakao.cafe.util.Checker;

import java.util.List;

public class PageSelector {
    public static final int SIZE = 5;

    private final boolean hasPrev;
    private final Pages pages;
    private final Page prevPage;
    private final Page activePage;
    private final Page nextPage;
    private final boolean hasNext;

    public PageSelector(final int page, final int totalArticles) {
        Checker.checkPage(page);
        final int maxPage = (int)Math.ceil((double)totalArticles / Page.MAX_ARTICLES);
        Checker.checkPage(maxPage);
        final int actualPage = Math.min(page, maxPage);
        final int start = (int)Math.floor((double)(actualPage - 1) / SIZE) * SIZE + 1;
        final int end = Math.min(maxPage, (int)Math.ceil((double)actualPage / SIZE) * SIZE);

        this.activePage = new Page(actualPage, true);
        this.prevPage = new Page(Math.max(1, start - 1), false);
        this.nextPage = new Page(Math.min(maxPage, end + 1), false);
        this.hasPrev = actualPage > SIZE;
        this.hasNext = actualPage <= (int)Math.floor((double)(maxPage - 1) / SIZE) * SIZE;
        this.pages = new Pages(start, end, actualPage);
    }

    public boolean isHasPrev() {
        return hasPrev;
    }

    public List<Page> getPageList() {
        return pages.getList();
    }

    public Page getPrevPage() {
        return prevPage;
    }

    public Page getActivePage() {
        return activePage;
    }

    public Page getNextPage() {
        return nextPage;
    }

    public boolean isHasNext() {
        return hasNext;
    }
}
