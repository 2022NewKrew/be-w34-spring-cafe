package com.kakao.cafe.service;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.MemoryArticleRepository;
import com.kakao.cafe.web.dto.ArticleCreateRequestDto;
import com.kakao.cafe.web.dto.ArticleDetailResponseDto;
import com.kakao.cafe.web.dto.ArticleResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ArticleServiceTest {

    ArticleService articleService;
    ArticleRepository articleRepository;

    @BeforeEach
    void setUp() {
        articleRepository = new MemoryArticleRepository();
        articleService = new ArticleService(articleRepository);
    }

    @DisplayName("게시글이 잘 등록되는가")
    @Test
    void postArticle() {

        //given
        String writer = "testId";
        String title = "test title";
        String contents = "test contents";
        LocalDateTime now = LocalDateTime.now();
        Article article = new Article(1L, writer, title, contents, now, now);

        //when
        ArticleCreateRequestDto requestDto = new ArticleCreateRequestDto(writer, title, contents);
        articleService.postArticle(requestDto);

        //then
        assertThat(articleRepository.findById(1L)).isEqualTo(article);
    }

    @DisplayName("게시글 목록이 잘 불러와지는가")
    @Test
    void getArticleList() {

        //given
        List<String> writerList = new ArrayList<>(Arrays.asList("testId1", "testId2", "testId3", "testId4"));
        List<String> titleList = new ArrayList<>(Arrays.asList("test title1", "test title2", "test title3", "test title4"));
        List<String> contentsList = new ArrayList<>(Arrays.asList("test contents1", "test contents2", "test contents3", "test contents4"));

        for (int i = 0; i < 4; i++) {
            articleService.postArticle(new ArticleCreateRequestDto(writerList.get(i), titleList.get(i), contentsList.get(i)));
        }

        //when
        List<ArticleResponseDto> articleList = articleService.getArticleList();

        //then
        assertThat(articleList.size()).isEqualTo(4);
    }

    @DisplayName("게시글 정보가 잘 불러와지는가")
    @Test
    void getArticleDetail() {

        //given
        String writer = "testId";
        String title = "test title";
        String contents = "test contents";
        Article article = new Article(1L, writer, title, contents, LocalDateTime.now(), LocalDateTime.now());
        ArticleDetailResponseDto expectedDto = ArticleDetailResponseDto.from(article);

        ArticleCreateRequestDto requestDto = new ArticleCreateRequestDto(writer, title, contents);
        articleService.postArticle(requestDto);

        //when
        ArticleDetailResponseDto responseDto = articleService.getArticleDetail(1L);

        //then
        assertThat(responseDto).isEqualTo(expectedDto);
    }
}