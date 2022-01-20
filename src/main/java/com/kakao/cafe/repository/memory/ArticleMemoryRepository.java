package com.kakao.cafe.repository.memory;

import com.kakao.cafe.dto.article.ArticleCreateCommand;
import com.kakao.cafe.domain.entity.Article;
import com.kakao.cafe.dto.article.ArticleModifyCommand;
import com.kakao.cafe.repository.ArticleRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class ArticleMemoryRepository implements ArticleRepository {
    private final List<Article> repository;

    public ArticleMemoryRepository() {
        this.repository = new ArrayList<>();
    }

    @Override
    public void store(ArticleCreateCommand acc) {
        Article article = new Article(nextId(),
                acc.getWriterId(),
                acc.getTitle(),
                acc.getContents(),
                LocalDateTime.now());
        this.repository.add(article);
    }

    @Override
    public Optional<Article> retrieve(Long id) {
        long target = Long.sum(id, -1L);
        return Optional.ofNullable(this.repository.get((int) target));
    }

    @Override
    public void modify(Long id, ArticleModifyCommand amc) {
        Article oldArticle = this.repository.get(id.intValue());
        Article article = new Article(id,
                oldArticle.getWriterId(),
                amc.getTitle(),
                amc.getContents(),
                LocalDateTime.now());
        this.repository.set(id.intValue(), article);
    }

    @Override
    public void delete(Long id) {
        this.repository.remove(id);
    }

    @Override
    public List<Article> toList() {
        return Collections.unmodifiableList(this.repository);
    }

    public Long nextId() {
        return this.repository.get(this.repository.size() - 1).getArticleId() + 1;
    }
}
