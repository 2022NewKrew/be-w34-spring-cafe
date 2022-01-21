package com.kakao.cafe.domain.article;

import com.kakao.cafe.util.Paging;
import com.kakao.cafe.util.PagingRequest;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    Optional<Article> findById(Long id);
    List<Article> findAll();
    Paging<Article> findByPageRequest(PagingRequest pageRequest);
    Long save(Article article);
    Long delete(Article article);
    Long deleteAll();
}
