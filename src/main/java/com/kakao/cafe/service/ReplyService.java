package com.kakao.cafe.service;

import com.kakao.cafe.domain.entity.Article;
import com.kakao.cafe.domain.entity.Reply;
import com.kakao.cafe.domain.entity.ReplyDraft;
import com.kakao.cafe.domain.entity.User;
import com.kakao.cafe.domain.exception.NoSuchObjectException;
import com.kakao.cafe.domain.exception.NoSuchUserException;
import com.kakao.cafe.domain.exception.UnauthorizedException;
import com.kakao.cafe.domain.repository.ArticleRepository;
import com.kakao.cafe.domain.repository.ReplyRepository;
import com.kakao.cafe.domain.repository.UserRepository;
import com.kakao.cafe.service.dto.ReplyDraftDto;
import com.kakao.cafe.service.dto.ReplyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReplyService {

    private final ReplyRepository repository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReplyService(
            ReplyRepository repository,
            ArticleRepository articleRepository,
            UserRepository userRepository
    ) {
        this.repository = repository;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    public ReplyDto create(long targetId, long authorId, ReplyDraftDto draft) {
        User author = userRepository.getById(authorId)
                .orElseThrow(NoSuchUserException::new);
        Article target = articleRepository.getById(targetId)
                .orElseThrow(NoSuchObjectException::new);
        ReplyDraft entity = draft.toEntity(target, author);
        return repository.create(entity).toDto();
    }

    public List<ReplyDto> list(long targetId) {
        return repository.list(targetId)
                .stream()
                .map(Reply::toDto)
                .collect(Collectors.toList());
    }

    public void delete(long targetId, long actorId) {
        User actor = userRepository.getById(actorId)
                .orElseThrow(NoSuchUserException::new);
        Reply target = repository.getById(targetId)
                .orElseThrow(NoSuchObjectException::new);
        if (!actor.canDelete(target)) {
            throw new UnauthorizedException();
        }
        repository.delete(targetId);
    }
}
