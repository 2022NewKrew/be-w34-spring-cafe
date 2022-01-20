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
    /**
     * 고유 ID 값입니다 [PK]
     */
    Long id;
    /**
     * 게시글 외래키
     */
    Long questionId;
    /**
     * 작성자 User Table key
     */
    Long memberId;
    /**
     * 작성자 이름 User Tabe userId
     */
    String writer;
    /**
     * 댓글 내용
     */
    String comment;
    /**
     * 작성시간
     */
    LocalDateTime createTime;
    /**
     * 댓글상태
     */
    ReplyStatus status;
}
