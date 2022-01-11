package com.kakao.cafe.adapter.out.infra.persistence.article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class ArticleInfoRepository {

    private final Map<Integer, ArticleInfoEntity> repository;

    public ArticleInfoRepository() {
        repository = new HashMap<>();
    }

    public void save(ArticleInfoEntity articleInfoEntity) {
        repository.put(articleInfoEntity.getIndex(), articleInfoEntity);
    }

    public List<ArticleInfoEntity> getAllArticleList() {
        return new ArrayList<>(repository.values());
    }

    public Optional<ArticleInfoEntity> findByIndex(int index) {
        return Optional.of(repository.get(index));
    }
}
