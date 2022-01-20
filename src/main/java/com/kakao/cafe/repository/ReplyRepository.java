package com.kakao.cafe.repository;

import com.kakao.cafe.dto.ReplyDTO;

import java.util.List;

public interface ReplyRepository {
    long insertReply(ReplyDTO reply);

    List<ReplyDTO> getArticleReplies(long articleId, long lastReplyId);

    ReplyDTO getReplyById(long replyId);

    int deleteReply(long replyId);

    int getOtherUserRepliesCount(long articleId, long userId);

    int deleteAllReplies(long id);
}
