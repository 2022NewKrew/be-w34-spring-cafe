package com.kakao.cafe.domain.article;

import java.util.List;
import java.util.Optional;

public interface FindArticlePort {

    List<Article> findAll();

    Optional<Article> findById(int index);
}
