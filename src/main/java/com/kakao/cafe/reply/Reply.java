package com.kakao.cafe.reply;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 댓글에 대한 Entity 입니다
 *
 * @author  jm.hong
 */
@Getter
@Setter
public class Reply {
    Long id;
    Long questionId;
    Long memberId;
    String writer;
    String comment;
    LocalDateTime createTime;
    ReplyStatus status;
}
