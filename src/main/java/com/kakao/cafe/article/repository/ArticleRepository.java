package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

@Repository
public class ArticleRepository {
    private static Long idSequence = 0L;
    private final static HashMap<Long, Article> articleDB = new HashMap<>();

    public Article find(Long id) {
        return articleDB.get(id);
    }

    public ArrayList<Article> findAll() {
        return new ArrayList<>(articleDB.values());
    }

    public Long persist(CreateArticleRequestDTO dto) {
        articleDB.put(idSequence, new Article(idSequence, dto.title, dto.authorId, LocalDateTime.now(), 0, dto.contents));
        idSequence += 1;
        return idSequence - 1;
    }

    public void increaseHit(Long id) {
        Article article = articleDB.get(id);
        article.increaseHit();
        articleDB.put(id, article);
    }
}
