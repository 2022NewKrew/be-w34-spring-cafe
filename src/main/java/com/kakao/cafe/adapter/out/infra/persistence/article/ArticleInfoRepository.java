package com.kakao.cafe.adapter.out.infra.persistence.article;

import java.util.List;
import java.util.Optional;

public interface ArticleInfoRepository {

    void save(ArticleInfoEntity articleInfoEntity);

    List<ArticleInfoEntity> getAllArticleList();

    Optional<ArticleInfoEntity> findByIndex(int index);
}
