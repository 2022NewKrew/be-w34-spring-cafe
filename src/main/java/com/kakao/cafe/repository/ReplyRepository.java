package com.kakao.cafe.repository;

import com.kakao.cafe.domain.entity.Reply;
import com.kakao.cafe.dto.reply.ReplyCreateCommand;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository {
    void store(ReplyCreateCommand rcc);
    Optional<Reply> retrieve(Long replyId);
    void delete(Long replyId);
    List<Reply> toList(Long articleId);
}
