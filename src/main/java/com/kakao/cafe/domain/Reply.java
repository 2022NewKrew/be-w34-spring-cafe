package com.kakao.cafe.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Reply {
    private Long id;
    private String contents;
    private Long articleId;
    private Long userId;
    private String time;
}
