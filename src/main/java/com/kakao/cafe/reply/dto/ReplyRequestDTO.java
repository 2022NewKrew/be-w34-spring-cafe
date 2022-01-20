package com.kakao.cafe.reply.dto;

import com.kakao.cafe.reply.domain.Reply;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@ToString
public class ReplyRequestDTO {

    private String content;

    public Reply toReply(Long articleId, Long memberId, LocalDateTime localDateTime) {
        return Reply.builder()
                .content(content)
                .articleId(articleId)
                .memberId(memberId)
                .createDateTime(localDateTime)
                .build();
    }
}
