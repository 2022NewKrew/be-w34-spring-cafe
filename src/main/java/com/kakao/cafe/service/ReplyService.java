package com.kakao.cafe.service;

import com.kakao.cafe.dto.ReplyDTO;

import java.util.List;

public interface ReplyService {
    long insertReply(ReplyDTO reply);

    List<ReplyDTO> getArticleReplies(long articleId, long userId);

    ReplyDTO getReplyById(long userId, long replyId);

    int deleteReply(long replyId);

    int getOtherUserRepliesCount(long articleId, long userId);

    int deleteAllReplies(long id);
}
