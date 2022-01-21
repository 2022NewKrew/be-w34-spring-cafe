package com.kakao.cafe.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Paging<T> {
    private List<T> pagingList;
    private int limit;
    private int offset;
    private int total;

    private final int pageRange = 5;

    public Paging(List<T> contents, int limit, int offset, int total) {
        this.pagingList = contents;
        this.limit = limit;
        this.offset = offset;
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }

    public int getPageRange() {
        return pageRange;
    }

    public int getStartRange() {
        int currentPage = getCurrentPage();
        return currentPage - (currentPage - 1) % getPageRange();
    }

    public int getEndRange() {
        return Math.min(getStartRange() + getPageRange() - 1, getTotalPagingNum());
    }

    public List<PageInfo> getRange() {
        return IntStream.rangeClosed(getStartRange(), getEndRange())
                .mapToObj(page -> new PageInfo(page, Math.max(0, limit * (page- 1))))
                .collect(Collectors.toList());
    }

    public boolean hasPrevious() {
        return (getStartRange() - 1) >= 1;
    }

    public boolean hasNext() {
        return (getStartRange() + pageRange) <= getTotalPagingNum();
    }

    public int getNextRangeStartOffset() {
        int startPage = getStartRange() + pageRange;
        return (startPage - 1) * limit;
    }

    public int getPreviousRangeEndOffset() {
        int previousPage = (getStartRange() - 1);
        return (previousPage - 1) * limit;
    }

    public int getCurrentPage() {
        int start = offset + 1;
        return (start / limit) + 1;
    }

    public List<T> getPagingList() {
        return pagingList;
    }

    public int getTotalPagingNum() {
        return (int)Math.ceil((double)total / limit);
    }

    static class PageInfo {
        private int offset;
        private int index;

        PageInfo(int index, int offset) {
            this.index = index;
            this.offset = offset;
        }
    }
}
