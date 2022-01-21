package com.kakao.cafe.web.service;

import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.web.repository.ReplyRepository;
import org.springframework.stereotype.Component;

@Component
public class ReplyService {
    private final ReplyRepository replyRepository;

    private ReplyService(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    public Reply getReplyById(int id) {
        return replyRepository.selectByReplyId(id);
    }

    public void addReply(Reply reply) {
        replyRepository.insertReply(reply);
    }

    public void deleteReply(int id) {
        replyRepository.deleteReply(id);
    }

    public void updateReply(int id, Reply updatedReply) {
        replyRepository.updateReply(id, updatedReply);
    }
}
