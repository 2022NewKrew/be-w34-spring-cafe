package com.kakao.cafe.service;

import com.kakao.cafe.dto.ArticleRequestDto;
import com.kakao.cafe.dto.ArticleResponseDto;
import com.kakao.cafe.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public void create(ArticleRequestDto articleRequestDto) {
        articleRepository.save(articleRequestDto);
    }

    public Optional<ArticleResponseDto> read(Long id) {
        return articleRepository.findById(id);
    }

    public List<ArticleResponseDto> readAll() {
        return articleRepository.findAll();
    }
}
