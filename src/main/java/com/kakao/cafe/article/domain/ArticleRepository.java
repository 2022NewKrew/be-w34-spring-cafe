package com.kakao.cafe.article.domain;

import com.kakao.cafe.article.dto.MultipleArticle;
import com.kakao.cafe.article.dto.SingleArticle;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    void save(Article article);

    Optional<SingleArticle> findById(Long id);

    List<MultipleArticle> findAll();
}
