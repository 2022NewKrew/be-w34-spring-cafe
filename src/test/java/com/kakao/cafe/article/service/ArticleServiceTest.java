package com.kakao.cafe.article.service;

import com.kakao.cafe.article.repository.ArticleRepository;
import com.kakao.cafe.article.repository.CreateArticleRequestDTO;
import com.kakao.cafe.user.repository.UserRepository;
import com.kakao.cafe.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArticleServiceTest {

    ArticleRepository articleRepository = ArticleRepository.getRepository();
    ArticleService articleService = new ArticleService();

    @BeforeEach
    void init() {
        articleRepository.clear();
    }

    @Test
    @DisplayName("게시글 저장 및 조회 확인")
    void testArticleSaveAndFind() throws Exception {
        // given

        // when

        // then

    }

//    public void createArticle(Long authorId, String title, String contents ){
//        articleRepository.persist(new CreateArticleRequestDTO(title, authorId, contents));
//    }
//
//    public ArticleReadResponseDTO getArticleReadViewDTO(Long id) {
//        articleRepository.increaseHit(id);

}
