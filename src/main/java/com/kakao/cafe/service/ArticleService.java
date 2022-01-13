package com.kakao.cafe.service;

import com.kakao.cafe.controller.dto.request.ArticleRegisterRequestDto;
import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.exception.ArticleNotFoundException;
import com.kakao.cafe.repository.article.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final UserService userService;
    private final ArticleRepository articleRepository;


    public void register(ArticleRegisterRequestDto articleRegisterRequestDto) {
        User writer = userService.findUserByUserId(articleRegisterRequestDto.getWriterId());
        Article article = Article.builder()
                .title(articleRegisterRequestDto.getTitle())
                .writer(writer)
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
