package com.kakao.cafe.model.post;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostWriteRequest {
    private String writer;
    private String title;
    private String content;

    @Builder
    public PostWriteRequest(String writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
    }
}
