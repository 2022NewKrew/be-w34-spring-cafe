package com.kakao.cafe.service;

import com.kakao.cafe.domain.entity.Article;
import com.kakao.cafe.domain.entity.Reply;
import com.kakao.cafe.domain.entity.User;
import com.kakao.cafe.domain.exception.NoSuchArticleException;
import com.kakao.cafe.domain.exception.NoSuchUserException;
import com.kakao.cafe.domain.repository.ArticleRepository;
import com.kakao.cafe.domain.repository.ReplyRepository;
import com.kakao.cafe.domain.repository.UserRepository;
import com.kakao.cafe.service.dto.ReplyDraftDto;
import com.kakao.cafe.service.dto.ReplyDto;
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

class ReplyServiceTest {

    private UserRepository userRepository;
    private ReplyRepository repository;
    private ArticleRepository articleRepository;
    private ReplyService subject;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        articleRepository = mock(ArticleRepository.class);
        repository = mock(ReplyRepository.class);
        subject = new ReplyService(repository, articleRepository, userRepository);
    }

    @Test
    void create() {
        User user = new User.Builder().build();
        when(userRepository.getById(anyLong())).thenReturn(Optional.of(user));
        Article article = new Article.Builder().build();
        when(articleRepository.getById(anyLong())).thenReturn(Optional.of(article));
        Reply reply = new Reply.Builder().build();
        when(repository.create(any())).thenReturn(reply);

        ReplyDraftDto draft = new ReplyDraftDto("content");
        ReplyDto result = subject.create(1L, 2L, draft);

        assertNotNull(result);
    }

    @Test
    void create_authorNotFound() {
        when(userRepository.getById(anyLong())).thenReturn(Optional.empty());

        ReplyDraftDto draft = new ReplyDraftDto("content");
        Executable body = () -> subject.create(1L, 2L, draft);

        assertThrowsExactly(NoSuchUserException.class, body);
    }

    @Test
    void create_targetNotFound() {
        User user = new User.Builder().build();
        when(userRepository.getById(anyLong())).thenReturn(Optional.of(user));
        when(articleRepository.getById(anyLong())).thenReturn(Optional.empty());

        ReplyDraftDto draft = new ReplyDraftDto("content");
        Executable body = () -> subject.create(1L, 2L, draft);

        assertThrowsExactly(NoSuchArticleException.class, body);
    }
}
