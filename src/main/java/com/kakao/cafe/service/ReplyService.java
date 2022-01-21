package com.kakao.cafe.service;

import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    public void create(Reply reply) {
        replyRepository.save(reply);
    }

    public List<Reply> list(int articleId) {
        return replyRepository.findAll(articleId);
    }

    public void delete(int id) {
        Reply reply = replyRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));

        replyRepository.delete(reply);
    }
}
