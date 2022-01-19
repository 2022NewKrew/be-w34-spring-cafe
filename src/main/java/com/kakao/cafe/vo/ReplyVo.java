package com.kakao.cafe.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReplyVo {
    private int replyId;
    private String writer;
    private String comment;
    private String date;
    private int articleId;
    private String userId;
}
