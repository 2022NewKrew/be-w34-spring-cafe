package com.kakao.cafe.articles;

import java.util.Objects;

public class ArticleContent {
    private String body;

    public ArticleContent(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleContent that = (ArticleContent) o;
        return Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body);
    }

    @Override
    public String toString() {
        return "ArticleContent{" +
                "body='" + body + '\'' +
                '}';
    }
}
