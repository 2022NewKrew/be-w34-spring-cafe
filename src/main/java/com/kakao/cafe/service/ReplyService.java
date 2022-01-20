package com.kakao.cafe.service;

import com.kakao.cafe.domain.reply.Reply;
import com.kakao.cafe.repository.ReplyInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class ReplyService {
    private final ReplyInterface replyRepository;

    public void join(Reply reply) {
        replyRepository.save(reply);
    }

    public Reply findOne(Long id) {
        Optional<Reply> reply = replyRepository.findById(id);
        if (reply.isPresent()) {
            return reply.get();
        }
        throw new IllegalStateException("not valid index");
    }

    public List<Reply> findReplyList(Long articleId) {
        return replyRepository.findByArticleId(articleId);
    }

    public void deleteReply(Long id) {
        replyRepository.delete(id);
    }
}
