package com.kakao.cafe.web.reply.repository;

import com.kakao.cafe.web.reply.domain.Reply;
import com.kakao.cafe.web.reply.dto.ReplyCreateDTO;
import com.kakao.cafe.web.reply.dto.ReplyUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository {
    Reply createReply(ReplyCreateDTO replyCreateDTO);

    List<Reply> getReplyList();

    List<Reply> getReplyListByArticleId(long articleId);

    List<Reply> getReplyListByWriter(String writer);

    Optional<Reply> getReplyById(long id);

    void deleteReplyById(long id);

    Reply updateReply(ReplyUpdateDTO replyUpdateDTO);
}
