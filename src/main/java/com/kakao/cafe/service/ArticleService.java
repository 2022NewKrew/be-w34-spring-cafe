package com.kakao.cafe.service;

import com.kakao.cafe.domain.entity.Article;
import com.kakao.cafe.domain.entity.Draft;
import com.kakao.cafe.domain.entity.Reply;
import com.kakao.cafe.domain.entity.User;
import com.kakao.cafe.domain.exception.NoSuchObjectException;
import com.kakao.cafe.domain.exception.NoSuchUserException;
import com.kakao.cafe.domain.exception.UnauthorizedException;
import com.kakao.cafe.domain.repository.ArticleRepository;
import com.kakao.cafe.domain.repository.ReplyRepository;
import com.kakao.cafe.domain.repository.UserRepository;
import com.kakao.cafe.service.dto.ArticleDto;
import com.kakao.cafe.service.dto.DraftDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository repository;
    private final UserRepository userRepository;
    private final ReplyRepository replyRepository;

    @Autowired
    public ArticleService(
            ArticleRepository repository,
            UserRepository userRepository,
            ReplyRepository replyRepository
    ) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.replyRepository = replyRepository;
    }

    public ArticleDto create(long authorId, DraftDto draft) {
        Optional<User> author = userRepository.getById(authorId);
        if (author.isEmpty()) {
            throw new NoSuchUserException();
        }
        Draft entity = draft.toEntity(author.get());
        return repository.create(entity).toDto();
    }

    public List<ArticleDto> list() {
        return repository.list()
                .stream()
                .map(Article::toDto)
                .collect(Collectors.toList());
    }

    public Optional<ArticleDto> getById(long id) {
        List<Reply> replies = replyRepository.list(id);
        return repository.getById(id).map(x -> x.toDto(replies));
    }

    public ArticleDto getEditableById(long userId, long id) {
        Optional<User> user = userRepository.getById(userId);
        Optional<Article> article = repository.getById(id);
        if (user.isEmpty()) {
            throw new NoSuchUserException();
        }
        if (article.isEmpty()) {
            throw new NoSuchObjectException();
        }
        if (user.get().getId() != article.get().getAuthorId()) {
            throw new UnauthorizedException();
        }
        return article.get().toDto();
    }

    public void update(Long authorId, long id, DraftDto draft) {
        Optional<User> author = userRepository.getById(authorId);
        if (author.isEmpty()) {
            throw new NoSuchUserException();
        }
        if (author.get().getId() != authorId) {
            throw new UnauthorizedException();
        }
        repository.update(id, draft.toEntity(author.get()));
    }

    public void delete(long authorId, long id) {
        Optional<User> author = userRepository.getById(authorId);
        if (author.isEmpty()) {
            throw new NoSuchUserException();
        }
        if (author.get().getId() != authorId) {
            throw new UnauthorizedException();
        }
        repository.delete(id);
    }
}
