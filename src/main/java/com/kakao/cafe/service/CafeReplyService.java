package com.kakao.cafe.service;

import com.kakao.cafe.model.Reply;

import java.util.List;

public interface CafeReplyService {
    List<Reply> getReplyList(int postId);
    boolean submitReply(Reply comment);
    boolean deleteReply(int commentId);
}
