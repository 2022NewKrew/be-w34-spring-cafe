package com.kakao.cafe.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    @Autowired
    public ReplyService(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    public void createReply(Reply reply) {
        replyRepository.save(reply);
    }

    public void updateReply(Reply reply) {
        replyRepository.update(reply);
    }

    public void deleteReply(Reply reply) {
        replyRepository.delete(reply);
    }

    public Reply findBySeq(long seq) {
        return replyRepository.findBySeq(seq).orElseThrow();
    }

    public List<Reply> findByArticleSeq(long articleSeq) {
        return replyRepository.findByArticleSeq(articleSeq).orElse(new ArrayList<>());
    }

    public List<Reply> getAllReplys() {
        return replyRepository.findAll();
    }

}
