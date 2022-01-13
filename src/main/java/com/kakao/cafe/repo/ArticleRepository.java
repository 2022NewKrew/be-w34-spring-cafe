package com.kakao.cafe.repo;

import com.kakao.cafe.domain.Article;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ArticleRepository {
    boolean add(@NonNull final Article article);

    Article find(final long idx);

    List<Article> getList();
}
