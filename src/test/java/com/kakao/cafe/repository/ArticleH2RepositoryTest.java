package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;
import java.util.Optional;

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
        Optional<Long> returnId = articleRepository.save(article);
        Article findArticle = articleRepository.findById(returnId.get().toString());
        assertEquals("testTitle", findArticle.getTitle());
        deleteId = returnId.get().toString();
    }

    @Test
    @DisplayName("모든 글 조회 테스트")
    void findall() {
        int beforeSize = articleRepository.getAllQuestions().size();
        Optional<Long> returnId = articleRepository.save(article);
        System.out.println(returnId);
        int afterSize = articleRepository.getAllQuestions().size();
        assertEquals(beforeSize + 1, afterSize);
        deleteId = returnId.get().toString();
    }

    @Test
    @DisplayName("id값으로 글 조회")
    void findById() {
        Optional<Long> returnId = Optional.ofNullable(articleRepository.save(article).orElse(99999L));
        Long findId = returnId.get() + 1L;
        Throwable exception = assertThrows(RuntimeException.class, () -> {
            articleRepository.findById(findId.toString());
        });
        assertEquals("존재하지 않는 Id 입니다", exception.getMessage());
        deleteId = returnId.get().toString();
    }

    @AfterEach
    void init() {
        articleRepository.deleteById(deleteId);
    }
}