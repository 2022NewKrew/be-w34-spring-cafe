package com.kakao.cafe.service;

import com.kakao.cafe.domain.entity.Article;
import com.kakao.cafe.domain.entity.User;
import com.kakao.cafe.domain.exception.NoSuchUserException;
import com.kakao.cafe.domain.exception.UnauthorizedException;
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
import static org.mockito.Mockito.*;

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

        DraftDto draft = new DraftDto("title", "content");
        ArticleDto result = subject.create(1L, draft);

        assertNotNull(result);
    }

    @Test
    void create_authorNotFound() {
        when(userRepository.getById(anyLong())).thenReturn(Optional.empty());

        DraftDto draft = new DraftDto("title", "content");
        Executable body = () -> subject.create(1L, draft);

        assertThrowsExactly(NoSuchUserException.class, body);
    }

    @Test
    void getEditableById() {
        User user = new User.Builder().id(1L).build();
        when(userRepository.getById(anyLong())).thenReturn(Optional.of(user));
        when(repository.getById(anyLong())).thenReturn(Optional.of(new Article.Builder().author(user).build()));

        ArticleDto result = subject.getEditableById(1L, 2L);

        assertNotNull(result);
    }

    @Test
    void getEditableById_unauthorized() {
        User user = new User.Builder().id(1L).build();
        User author = new User.Builder().id(3L).build();
        when(userRepository.getById(anyLong())).thenReturn(Optional.of(user));
        when(repository.getById(anyLong())).thenReturn(Optional.of(new Article.Builder().author(author).build()));

        Executable body = () -> subject.getEditableById(1L, 2L);

        assertThrowsExactly(UnauthorizedException.class, body);
    }

    @Test
    void update() {
        when(userRepository.getById(anyLong())).thenReturn(Optional.of(new User.Builder().id(1L).build()));

        subject.update(1L, 2L, new DraftDto("title", "content"));

        verify(repository).update(eq(2L), any());
    }

    @Test
    void update_unauthorized() {
        when(userRepository.getById(anyLong())).thenReturn(Optional.of(new User.Builder().id(3L).build()));

        Executable body = () -> subject.update(1L, 2L, new DraftDto("title", "content"));

        assertThrowsExactly(UnauthorizedException.class, body);
    }

    @Test
    void delete() {
        when(userRepository.getById(anyLong())).thenReturn(Optional.of(new User.Builder().id(1L).build()));

        subject.delete(1L, 2L);

        verify(repository).delete(2L);
    }

    @Test
    void delete_unauthorized() {
        when(userRepository.getById(anyLong())).thenReturn(Optional.of(new User.Builder().id(3L).build()));

        Executable body = () -> subject.delete(1L, 2L);

        assertThrowsExactly(UnauthorizedException.class, body);
    }
}
