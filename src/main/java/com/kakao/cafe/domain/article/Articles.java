package com.kakao.cafe.domain.article;

import java.util.List;

public class Articles {
    private int totalCount;
    private List<Article> articleList;
    private boolean hasPrev = true;
    private List<Integer> pageList;
    private boolean hasNext = true;
    private int prev;
    private int next;

    public int getPrev() {
        return prev;
    }

    public void setPrev(int prev) {
        this.prev = prev;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public List<Integer> getPageList() {
        return pageList;
    }

    public void setPageList(List<Integer> pageList) {
        this.pageList = pageList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public boolean isHasPrev() {
        return hasPrev;
    }

    public void setHasPrev(boolean hasPrev) {
        this.hasPrev = hasPrev;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    @Override
    public String toString() {
        return "Articles{" +
                "totalCount=" + totalCount +
                ", articleList=" + articleList +
                ", hasPrev=" + hasPrev +
                ", pageList=" + pageList +
                ", hasNext=" + hasNext +
                '}';
    }
}
