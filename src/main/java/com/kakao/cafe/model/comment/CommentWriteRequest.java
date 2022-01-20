package com.kakao.cafe.model.comment;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentWriteRequest {
    private String content;

    @Builder
    public CommentWriteRequest(String content) {
        this.content = content;
    }
    
}
