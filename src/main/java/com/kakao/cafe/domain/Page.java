package com.kakao.cafe.domain;

import com.kakao.cafe.util.Checker;

import java.util.Objects;

public class Page {
    public static final int MAX_ARTICLES = 15;

    private final int page;
    private final boolean isSelected;

    public Page(final int page, final boolean isSelected) {
        Checker.checkPage(page);
        this.page = page;
        this.isSelected = isSelected;
    }

    public int getPage() {
        return page;
    }

    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page page1 = (Page) o;
        return page == page1.page && isSelected == page1.isSelected;
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, isSelected);
    }
}
