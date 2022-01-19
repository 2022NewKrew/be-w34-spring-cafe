package com.kakao.cafe.repository.article;

import com.kakao.cafe.entity.ArticleEntity;
import com.kakao.cafe.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<ArticleEntity, Long> {
    void updateViewsById(Long primaryKey, int views);

    void deleteById(Long primaryKey);

    void updateTitleAndContentById(Long primaryKey, String title, String content);
}
