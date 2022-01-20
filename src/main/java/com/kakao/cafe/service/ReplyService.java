package com.kakao.cafe.service;

import com.kakao.cafe.domain.reply.Reply;
import com.kakao.cafe.repository.ReplyInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class ReplyService {
    private final ReplyInterface replyRepository;

    public void join(Reply reply) {
        replyRepository.save(reply);
    }

    public List<Reply> findReplyList(Long articleId) {
        return replyRepository.findByArticleId(articleId);
    }

    public void deleteReply(Long id) {
        replyRepository.delete(id);
    }
}
