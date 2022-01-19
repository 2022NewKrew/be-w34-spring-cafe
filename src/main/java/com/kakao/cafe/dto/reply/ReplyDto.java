package com.kakao.cafe.dto.reply;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReplyDto {
    private int replyId;
    private String writer;
    private String comment;
    private String date;
    private int articleId;
    private String userId;
}
