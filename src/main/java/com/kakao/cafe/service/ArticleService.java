package com.kakao.cafe.service;

import com.kakao.cafe.domain.entity.Article;
import com.kakao.cafe.domain.entity.Draft;
import com.kakao.cafe.domain.entity.User;
import com.kakao.cafe.domain.exception.NoSuchUserException;
import com.kakao.cafe.domain.repository.ArticleRepository;
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

    @Autowired
    public ArticleService(ArticleRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
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
        return repository.getById(id).map(Article::toDto);
    }
}
