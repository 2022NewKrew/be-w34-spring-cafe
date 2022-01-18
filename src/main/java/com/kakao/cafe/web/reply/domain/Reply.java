package com.kakao.cafe.web.reply.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reply {
    private long id;
    private long articleId;
    private String writer;
    private String contents;
    private String createdTime;
    private String modifiedTime;
    private boolean deleted;
}
