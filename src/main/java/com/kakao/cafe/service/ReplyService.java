package com.kakao.cafe.service;

import com.kakao.cafe.domain.reply.Reply;
import com.kakao.cafe.repository.RepositoryInterface;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReplyService {
    private final RepositoryInterface<Reply> replyRepository;

    public ReplyService(RepositoryInterface<Reply> replyRepository) {
        this.replyRepository = replyRepository;
    }
}
