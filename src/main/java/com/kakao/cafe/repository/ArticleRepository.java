package com.kakao.cafe.repository;

import com.kakao.cafe.dto.ArticleRequestDto;
import com.kakao.cafe.dto.ArticleResponseDto;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    void save(ArticleRequestDto ArticleResponseDto);
    Optional<ArticleResponseDto> findById(Long id);
    List<ArticleResponseDto> findAll();
}
