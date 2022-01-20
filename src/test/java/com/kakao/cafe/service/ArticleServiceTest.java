package com.kakao.cafe.service;

import com.kakao.cafe.repository.*;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.ArticleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.mock;

@SpringBootTest
public class ArticleServiceTest {
    private ArticleService articleService;
    private ArticleRepository mockArticleRepository;
    private UserRepository mockUserRepository;

    public ArticleServiceTest() {
        mockArticleRepository = mock(ArticleDao.class);
        mockUserRepository = mock(UserDao.class);
        articleService = new ArticleServiceImpl(mockArticleRepository, mockUserRepository);
    }

}
