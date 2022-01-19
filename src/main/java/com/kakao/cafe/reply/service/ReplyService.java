package com.kakao.cafe.reply.service;

import com.kakao.cafe.article.exception.ArticleNotFoundException;
import com.kakao.cafe.article.repository.ArticleRepository;
import com.kakao.cafe.reply.dto.request.ReplyCreateRequest;
import com.kakao.cafe.reply.entity.Reply;
import com.kakao.cafe.reply.mapper.ReplyMapper;
import com.kakao.cafe.reply.repository.ReplyRepository;
import com.kakao.cafe.user.entity.User;
import com.kakao.cafe.user.mapper.exception.UserNotFoundException;
import com.kakao.cafe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;
    private final ReplyMapper replyMapper;

    public void createReply(ReplyCreateRequest req, Long userPK, Long articleId) {
        User writer = this.userRepository.findById(userPK)
                                         .orElseThrow(UserNotFoundException::new);
        this.articleRepository.findById(articleId)
                              .orElseThrow(ArticleNotFoundException::new);

        Reply reply = this.replyMapper.replyCreateRequestToEntity(req);
        reply.setWriter(writer);

        this.replyRepository.save(reply, articleId);
    }
}
