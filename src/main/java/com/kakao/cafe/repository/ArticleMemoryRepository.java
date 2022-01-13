package com.kakao.cafe.repository;

import com.kakao.cafe.domain.dto.ArticleCreateCommand;
import com.kakao.cafe.domain.entity.Article;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleMemoryRepository implements ArticleRepository {
    private final List<Article> repository;

    public ArticleMemoryRepository() {
        this.repository = new ArrayList<>();
    }

    @Override
    public void store(ArticleCreateCommand acc) {
        Article article = new Article(nextId(), acc.getWriter(), acc.getTitle(), acc.getContents(), LocalDateTime.now());
        this.repository.add(article);
    }

    @Override
    public Article retrieve(Long id) {
        Long target = Long.sum(id, -1L);
        return this.repository.get(target.intValue());
    }

    @Override
    public void modify(Long id, Article article) {
        this.repository.set(id.intValue(), article);
    }

    @Override
    public void delete(Long id) {
        this.repository.remove(id);
    }

    @Override
    public List<Article> toList() {
        return this.repository;
    }

    public Long nextId() {
        return this.repository.get(this.repository.size() - 1).getArticleId() + 1;
    }
}
