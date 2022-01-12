package com.kakao.cafe.repository;

import com.kakao.cafe.domain.dto.ArticleCreateCommand;
import com.kakao.cafe.domain.entity.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleRepository {
    private final List<Article> repository;

    public ArticleRepository() {
        this.repository = new ArrayList<>();
    }

    public void store(ArticleCreateCommand acc) {
        Article article = new Article(nextId(), acc.getWriter(), acc.getTitle(), acc.getContents());
        this.repository.add(article);
    }

    public Article retrieve(int id) {
        return this.repository.get(id);
    }

    public void modify(int id, Article article) {
        this.repository.set(id, article);
    }

    public Article delete(int id) {
        return this.repository.remove(id);
    }

    public List<Article> toList() {
        return this.repository;
    }

    public int nextId() {
        return this.repository.size();
    }
}
