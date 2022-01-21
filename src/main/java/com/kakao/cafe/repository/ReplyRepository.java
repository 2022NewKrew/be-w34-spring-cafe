package com.kakao.cafe.repository;

import com.kakao.cafe.domain.dto.ReplyWriteDto;

public interface ReplyRepository {
    void postReply(ReplyWriteDto replyWriteDto);
}
