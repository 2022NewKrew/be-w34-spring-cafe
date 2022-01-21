package com.kakao.cafe.service.reply;

import com.kakao.cafe.dto.reply.ReplyRequest;
import com.kakao.cafe.dto.reply.ReplyResponse;
import com.kakao.cafe.dto.reply.ReplyUpdateRequest;

import java.util.List;

public interface ReplyService {
    void addReply(ReplyRequest replyRequest);
    List<ReplyResponse> findReplies(Long articleId);
    ReplyResponse findReplyById(Long replyId);
    void modifyReply(ReplyUpdateRequest replyUpdateRequest, Boolean removal);
}
