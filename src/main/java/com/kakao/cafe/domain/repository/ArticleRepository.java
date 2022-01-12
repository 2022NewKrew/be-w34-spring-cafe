package com.kakao.cafe.domain.repository;

import com.kakao.cafe.domain.entity.Article;
import com.kakao.cafe.domain.entity.Draft;
import org.springframework.lang.Nullable;

import java.util.List;

public interface ArticleRepository {

    Article create(Draft draft);
    List<Article> list();
    @Nullable
    Article getById(long id);
}
