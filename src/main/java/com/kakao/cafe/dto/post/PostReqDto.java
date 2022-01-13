package com.kakao.cafe.dto.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostReqDto {
    private String writer;
    private String title;
    private String contents;
}
