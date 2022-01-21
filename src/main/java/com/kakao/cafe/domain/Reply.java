package com.kakao.cafe.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Reply {

    private Long id;
    private Long articleId;
    private User writer;
    private String comment;

    public String getWriterId() {
        return writer.getUserId();
    }


}
