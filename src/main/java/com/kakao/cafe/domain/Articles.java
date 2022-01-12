package com.kakao.cafe.domain;

import com.kakao.cafe.dto.ArticleDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Articles {
    private final List<Article> articles = new ArrayList<>();

    public void addPost(ArticleDto articleDto) {
        this.articles.add(new Article(articles.size() + 1, articleDto));
    }

    public Article findById(int id){
        return articles.stream().filter(article -> article.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
