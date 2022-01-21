package com.kakao.cafe.reply.service;

import com.kakao.cafe.reply.domain.Reply;
import com.kakao.cafe.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    public void createReply(Reply reply) {
        replyRepository.save(reply);
    }

    public List<Reply> findReplyByArticleId(Long articleId) {
        return replyRepository.findByArticleId(articleId);
    }

}
