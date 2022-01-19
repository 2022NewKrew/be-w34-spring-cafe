package com.kakao.cafe.domain.article;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ArticleDeleted {

    private static final Map<Boolean, ArticleDeleted> ARTICLE_DELETED_CACHE = new HashMap<>();

    static {
        ARTICLE_DELETED_CACHE.put(true, new ArticleDeleted(true));
        ARTICLE_DELETED_CACHE.put(false, new ArticleDeleted(false));
    }

    private final boolean value;

    private ArticleDeleted(boolean value) {
        this.value = value;
    }

    public static ArticleDeleted from(boolean value) {
        return ARTICLE_DELETED_CACHE.get(value);
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ArticleDeleted that = (ArticleDeleted) o;
        return value == that.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
