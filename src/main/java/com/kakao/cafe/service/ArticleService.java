package com.kakao.cafe.service;

import com.kakao.cafe.dto.ArticleDto;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.NoSuchElementException;

public interface ArticleService {
    void add(@NonNull final ArticleDto articleDto);
    List<ArticleDto> getList();
    ArticleDto getArticle(final long idx) throws NoSuchElementException;
}
