package com.kakao.cafe.service;

import com.kakao.cafe.dto.ReplyDTO;
import com.kakao.cafe.dto.ReplyDTO.Create;
import com.kakao.cafe.dto.ReplyDTO.Result;
import com.kakao.cafe.dto.ReplyDTO.Update;
import com.kakao.cafe.error.ErrorCode;
import com.kakao.cafe.error.exception.ArticleNotFoundException;
import com.kakao.cafe.error.exception.ForbiddenAccessException;
import com.kakao.cafe.error.exception.ReplyNotFoundException;
import com.kakao.cafe.error.exception.UserNotFoundException;
import com.kakao.cafe.persistence.model.Article;
import com.kakao.cafe.persistence.model.AuthInfo;
import com.kakao.cafe.persistence.model.Reply;
import com.kakao.cafe.persistence.model.User;
import com.kakao.cafe.persistence.repository.ArticleRepository;
import com.kakao.cafe.persistence.repository.ReplyRepository;
import com.kakao.cafe.persistence.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private static final Logger logger = LoggerFactory.getLogger(ReplyService.class);

    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Transactional
    public void create(AuthInfo authInfo, Long articleId, Create createDTO) {
        User foundUser = userRepository.findUserByUid(authInfo.getUid())
            .orElseThrow(() -> new UserNotFoundException(ErrorCode.NOT_FOUND, authInfo.getUid()));

        articleRepository.findArticleById(articleId)
            .orElseThrow(() -> new ArticleNotFoundException(ErrorCode.NOT_FOUND, articleId));

        Reply reply = Reply.builder()
            .articleId(articleId)
            .uid(authInfo.getUid())
            .userName(foundUser.getName())
            .body(createDTO.getBody())
            .createdAt(LocalDateTime.now())
            .build();

        replyRepository.save(reply);
        logger.info("Reply Created : {}", reply);
    }

    @Transactional(readOnly = true)
    public List<Result> readByArticleId(Long articleId) {
        List<Reply> replies = replyRepository.findByArticleId(articleId);
        logger.info("Read All Replies for Article {} :: {}", articleId, replies);

        return replies.stream()
            .map(Result::from)
            .collect(Collectors.toUnmodifiableList());
    }

    @Transactional(readOnly = true)
    public Result readById(Long id) {
        Reply foundReply = replyRepository.findById(id)
            .orElseThrow(() -> new ReplyNotFoundException(ErrorCode.NOT_FOUND, id));
        logger.info("Read Reply by [ID : {}] :: {}", id, foundReply);

        return Result.from(foundReply);
    }

    @Transactional
    public void update(AuthInfo authInfo, Long id, Update updateDTO) {
        Reply foundReply = replyRepository.findById(id)
            .orElseThrow(() -> new ReplyNotFoundException(ErrorCode.NOT_FOUND, id));
        if (!authInfo.matchUid(foundReply.getUid())) {
            throw new ForbiddenAccessException(ErrorCode.FORBIDDEN_ACCESS, "Update Reply " + id);
        }

        replyRepository.update(id, updateDTO.getBody());
        logger.info("Update Reply [ID : {}] :: Body {}", id, updateDTO.getBody());
    }

    @Transactional
    public void delete(AuthInfo authInfo, Long id) {
        Reply foundReply = replyRepository.findById(id)
            .orElseThrow(() -> new ReplyNotFoundException(ErrorCode.NOT_FOUND, id));
        if (!authInfo.matchUid(foundReply.getUid())) {
            throw new ForbiddenAccessException(ErrorCode.FORBIDDEN_ACCESS, "Delete Reply " + id);
        }

        replyRepository.delete(id);
        logger.info("Delete Reply [ID : {}]", id);
    }
}
