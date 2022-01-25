package com.kakao.cafe.service;

import com.kakao.cafe.dto.ArticleDto;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.NoSuchElementException;

public interface ArticleService {
    void add(@NonNull final ArticleDto articleDto);
    long countOfValid();
    List<ArticleDto> getDtoList(final long size, final long offset);
    ArticleDto getDto(final long idx) throws NoSuchElementException;
    ArticleDto getDto(final long idx, final String currentUserId) throws NoSuchElementException;
    boolean update(@NonNull final ArticleDto articleDto);
    boolean delete(@NonNull final long idx);
}
