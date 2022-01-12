package com.kakao.cafe.service;

import com.kakao.cafe.domain.entity.Article;
import com.kakao.cafe.domain.entity.Draft;
import com.kakao.cafe.domain.entity.Session;
import com.kakao.cafe.domain.entity.User;
import com.kakao.cafe.domain.repository.ArticleRepository;
import com.kakao.cafe.domain.repository.UserRepository;
import com.kakao.cafe.service.dto.ArticleDto;
import com.kakao.cafe.service.dto.DraftDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Nullable
    public ArticleDto create(String sessionId, DraftDto draft) {
        Session session = userRepository.getSession(sessionId);
        if (session == null) {
            return null;
        }
        long ownerId = session.getUserId();
        User owner = userRepository.getById(ownerId);
        Draft entity = draft.toEntity(owner);
        return repository.create(entity).toDto();
    }

    public List<ArticleDto> list() {
        return repository.list()
                .stream()
                .map(Article::toDto)
                .collect(Collectors.toList());
    }

    @Nullable
    public ArticleDto getById(long id) {
        Article found = repository.getById(id);
        if (found == null) {
            return null;
        }
        return found.toDto();
    }
}
