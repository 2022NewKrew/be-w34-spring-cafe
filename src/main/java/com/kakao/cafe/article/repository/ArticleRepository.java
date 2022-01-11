package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class ArticleRepository {
    private static Long idSequence = 0L;
    private final static HashMap<Long, Article> articleDB = new HashMap<>();

    private final static ArticleRepository articleRepository = new ArticleRepository();

    public static ArticleRepository getRepository() {
        return articleRepository;
    }

    private ArticleRepository() {
    }

    public void clear() {
        idSequence = 0L;
        articleDB.clear();
    }

    public Article find(Long id) {
        return articleDB.get(id);
    }

    public ArrayList<Article> findAll() {
        return new ArrayList<>(articleDB.values());
    }

    public Long persist(CreateArticleRequestDTO dto) {
        articleDB.put(idSequence, new Article(idSequence, dto.title, dto.authorId, LocalDateTime.now(), 0, dto.contents));
        idSequence += 1;
        return idSequence;
    }

    public void increaseHit(Long id) {
        Article article = articleDB.get(id);
        article.increaseHit();
        articleDB.put(id, article);
    }
}
