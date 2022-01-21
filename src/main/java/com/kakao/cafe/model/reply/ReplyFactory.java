package com.kakao.cafe.model.reply;

import com.kakao.cafe.model.user.UserId;
import com.kakao.cafe.service.reply.dto.ReplyCreateDto;
import java.time.LocalDateTime;

public class ReplyFactory {

    public static Reply getReply(ReplyCreateDto replyCreateDto) {
        return new Reply(
                0,
                replyCreateDto.getArticleId(),
                new UserId(replyCreateDto.getUserId()),
                new Comment(replyCreateDto.getComment())
        );
    }

    public static Reply getReply(int id, int articleId, String userId, String comment,
            LocalDateTime createDate, boolean deleted) {
        return new Reply(
                id,
                articleId,
                new UserId(userId),
                new Comment(comment),
                createDate,
                deleted
        );
    }
}
