package com.kakao.cafe.reply.dto;

import com.kakao.cafe.reply.Reply;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ReplyDto {
    Long id;
    String writer;
    String comment;
    LocalDateTime createTime;
    boolean thisIsMine;

    public static ReplyDto of(Reply reply, Long memberId) {

        ReplyDto replyDto = new ReplyDto();

        replyDto.setId(reply.getId());
        replyDto.setWriter(reply.getWriter());
        replyDto.setComment(reply.getComment());
        replyDto.setCreateTime(reply.getCreateTime());
        replyDto.setThisIsMine(memberId.equals(reply.getMemberId()));

        return replyDto;
    }

    public static List<ReplyDto> of(List<Reply> replies, Long memberId) {
        return replies
                .stream()
                .map(r -> ReplyDto.of(r, memberId))
                .collect(Collectors.toList());
    }
}
