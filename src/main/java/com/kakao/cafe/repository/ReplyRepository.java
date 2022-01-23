package com.kakao.cafe.repository;

import com.kakao.cafe.domain.dto.ReplyWriteDto;
import com.kakao.cafe.domain.model.Reply;

import java.util.List;

public interface ReplyRepository {
    void postReply(ReplyWriteDto replyWriteDto);
    List<Reply> findAllReplies(String articleId);
    void deleteReply(String id);
    String findUserIdOfReply(String id);
}
