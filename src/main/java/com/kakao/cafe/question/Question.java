package com.kakao.cafe.question;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 질문글 엔티티 입니다.
 *
 * @author jm.hong
 */
@Getter @Setter
public class Question {
    /**
     * 고유 ID 값입니다 [PK]
     */
    private Long id;
    /**
     * 외래키
     */
    private Long memberId;
    /**
     * 질문글 작성자(User.userId) 입니다
     */
    private String writer;
    /**
     * 질문글 제목 입니다.
     */
    private String title;
    /**
     * 질문글 내용 입니다.
     */
    private String contents;
    /**
     * 게시글 생성 시간 입니다.
     */
    private LocalDateTime createTime;

    /**
     * 시스템 시간으로 Question.createTime 을 업데이트합니다.
     */
    public void updateTime() {
        createTime = LocalDateTime.now();
    }
}
