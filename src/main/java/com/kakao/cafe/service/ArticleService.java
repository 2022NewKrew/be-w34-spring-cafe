package com.kakao.cafe.service;

import com.kakao.cafe.controller.dto.ArticleRegisterRequestDto;
import com.kakao.cafe.domain.Article;
import com.kakao.cafe.exception.ArticleNotFoundException;
import com.kakao.cafe.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;


    public void register(ArticleRegisterRequestDto articleRegisterRequestDto) {
        Article article = Article.builder()
                .title(articleRegisterRequestDto.getTitle())
                .writer(articleRegisterRequestDto.getWriter())
                .contents(articleRegisterRequestDto.getContents())
                .build();
        articleRepository.save(article);
    }

    public Article findById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ArticleNotFoundException("게시글을 찾을수 없습니다.");
                });
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }
}
