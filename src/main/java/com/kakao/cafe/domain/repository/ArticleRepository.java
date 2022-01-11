package com.kakao.cafe.domain.repository;

import com.kakao.cafe.domain.entity.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleRepository implements BaseRepository<Article> {
    private final List<Article> repository;

    public ArticleRepository() {
        this.repository = new ArrayList<>();
    }

    @Override
    public void store(Article article) {
        this.repository.add(article);
    }

    @Override
    public Article retrieve(int id) {
        return this.repository.get(id);
    }

    @Override
    public void modify(int id, Article article) {
        this.repository.set(id, article);
    }

    @Override
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
