package com.kakao.cafe.service.reply;

import com.kakao.cafe.dto.ReplyDto;

import java.util.List;

public interface ReplyService {
    void writeReply(ReplyDto replyDto);

    void deleteReply(Long replyId);

    List<ReplyDto> allReplies();

    List<ReplyDto> repliesByArticle(Long articleId);
}
