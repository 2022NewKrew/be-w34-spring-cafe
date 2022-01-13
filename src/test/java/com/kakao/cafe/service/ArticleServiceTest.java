package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.ArticleDTO;
import com.kakao.cafe.repository.article.ArticleNoBdUseRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ArticleServiceTest {
    ArticleService articleService;
    ArticleNoBdUseRepository articleNoBdUseRepository;

    @BeforeEach
    public void beforeEach(){
        articleNoBdUseRepository = new ArticleNoBdUseRepository();
        articleService = new ArticleService(articleNoBdUseRepository);
    }

    @AfterEach
    public void afterEach(){
        articleNoBdUseRepository.clearStore();
    }

    @Test
    @DisplayName("회원가입")
    void join() {
        //given
        ArticleDTO articleDTO = new ArticleDTO("aa", "aa", "aa");

        //when
        int id = articleService.join(articleDTO);

        //then
        Article findArticle = articleService.findOne(id).get();
        assertThat(articleDTO.getId()).isEqualTo(findArticle.getId());
    }

    @Test
    @DisplayName("전체 조회")
    void findAll() {
        //givin
        ArticleDTO articleDTO = new ArticleDTO("aa", "aa", "aa");
        articleService.join(articleDTO);

        ArticleDTO articleDTO2 = new ArticleDTO("bb", "bb", "bb");
        articleService.join(articleDTO2);

        //when
        List<Article> articles = articleService.findAll();

        //then
        assertThat(articles.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("하나 조회")
    void findOne() {
        //givin
        ArticleDTO articleDTO = new ArticleDTO("aa", "aa", "aa");
        articleService.join(articleDTO);

        //when
        Article article = articleService.findOne(articleDTO.getId()).get();

        //then
        assertThat(articleDTO.getId()).isEqualTo(article.getId());
    }
}
