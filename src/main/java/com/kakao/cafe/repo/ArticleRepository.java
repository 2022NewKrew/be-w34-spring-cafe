package com.kakao.cafe.repo;

import com.kakao.cafe.entity.Article;
import com.kakao.cafe.dto.ArticleDto;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ArticleRepository {
    boolean add(@NonNull final Article article);

    ArticleDto getDto(final long idx);

    List<ArticleDto> getDtoList();

    boolean update(final long idx, @NonNull final Article article);

    boolean delete(final long idx);
}
