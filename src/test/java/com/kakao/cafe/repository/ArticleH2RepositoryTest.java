package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleH2RepositoryTest {
    private static Article article;
    private String deleteId = "0";
    @Autowired
    private ArticleRepository articleRepository;

    @BeforeEach
    void setUp() {
        article = new Article();
        article.setWriter("testWriter");
        article.setTitle("testTitle");
        article.setContents("testContents");
    }

    @Test
    @DisplayName("글 저장 테스트")
    void save() {
        Long returnId = articleRepository.save(article);
        Article findArticle = articleRepository.findById(returnId.toString());
        assertEquals("testTitle", findArticle.getTitle());
        deleteId = returnId.toString();
    }

    @Test
    @DisplayName("모든 글 조회 테스트")
    void findall() {
        int beforeSize = articleRepository.getAllQuestions().size();
        Long returnId = articleRepository.save(article);
        int afterSize = articleRepository.getAllQuestions().size();
        assertEquals(beforeSize + 1, afterSize);
        deleteId = returnId.toString();
    }

    @Test
    @DisplayName("id값으로 글 조회")
    void findById() {
        Long returnId = articleRepository.save(article);
        Long findId = returnId + 1L;
        Throwable exception = assertThrows(RuntimeException.class, () -> {
            articleRepository.findById(findId.toString());
        });
        assertEquals("존재하지 않는 Id 입니다", exception.getMessage());
        deleteId = returnId.toString();
    }

    @AfterEach
    void init() {
        articleRepository.deleteById(deleteId);
    }
}