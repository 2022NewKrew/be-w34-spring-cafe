package com.kakao.cafe.domain;

import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Articles {
    private static final List<Article> list = new ArrayList<>();

    public void add(@NonNull final Article article) {
        list.add(article);
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
