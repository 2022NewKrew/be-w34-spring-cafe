package com.kakao.cafe.service;

import com.kakao.cafe.domain.entity.Article;
import com.kakao.cafe.domain.entity.User;
import com.kakao.cafe.domain.exception.NoSuchUserException;
import com.kakao.cafe.domain.repository.ArticleRepository;
import com.kakao.cafe.domain.repository.UserRepository;
import com.kakao.cafe.service.dto.ArticleDto;
import com.kakao.cafe.service.dto.DraftDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ArticleServiceTest {

    private UserRepository userRepository;
    private ArticleRepository repository;
    private ArticleService subject;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        repository = mock(ArticleRepository.class);
        subject = new ArticleService(repository, userRepository);
    }

    @Test
    void create() {
        User user = new User.Builder().build();
        when(userRepository.getById(anyLong())).thenReturn(Optional.of(user));
        Article article = new Article.Builder().build();
        when(repository.create(any())).thenReturn(article);

        DraftDto draft = new DraftDto("author", "title", "content");
        ArticleDto result = subject.create(1L, draft);

        assertNotNull(result);
    }

    @Test
    void create_ownerNotFound() {
        when(userRepository.getById(anyLong())).thenReturn(Optional.empty());

        DraftDto draft = new DraftDto("author", "title", "content");
        Executable body = () -> subject.create(1L, draft);

        assertThrowsExactly(NoSuchUserException.class, body);
    }
}
