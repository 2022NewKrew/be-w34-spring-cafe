package com.kakao.cafe.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Articles {
    private static final List<Article> list = new ArrayList<>();

    public void add(final Article article) {
        list.add(Objects.requireNonNull(article));
    }

    public Article find(final long idx) {
        for (Article a : list) {
            if (idx == a.getIdx()) {
                return a;
            }
        }
        return Article.NONE;
    }

    public List<Article> getList() {
        return Collections.unmodifiableList(list);
    }
}
