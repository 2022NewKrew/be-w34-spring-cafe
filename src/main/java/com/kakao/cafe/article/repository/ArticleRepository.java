package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.entity.ArticleEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ArticleRepository {
    private static final Map<Long, ArticleEntity> db = new HashMap<>();
    private static Long seq = 1L;

    public ArticleEntity save(ArticleEntity articleEntity) {
        articleEntity.setId(seq++);
        db.put(articleEntity.getId(), articleEntity);
        return articleEntity;
    }

    public List<ArticleEntity> findAll() {
        return new ArrayList<>(db.values());
    }
}
