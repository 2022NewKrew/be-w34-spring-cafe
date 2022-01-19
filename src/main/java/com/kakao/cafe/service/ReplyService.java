package com.kakao.cafe.service;

import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.ReplyInfoResponse;
import com.kakao.cafe.dto.ReplyRequest;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.ReplyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    public ReplyService(ReplyRepository replyRepository, ArticleRepository articleRepository) {
        this.replyRepository = replyRepository;
    }

    public void create(Reply reply) {
        replyRepository.save(reply);
    }

    public List<ReplyInfoResponse> getReplyList(Long articleId) {

        return replyRepository.findAll(articleId);
    }

    public void applyVisibleEdit(List<ReplyInfoResponse> replys, User user) {
        replys.stream().
                filter(reply -> reply.getWriterId().equals(user.getId())).
                forEach(reply -> reply.setIsVisibleEdit(true));
    }
}
