package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDto {

    private Long memberId;
    private String userId;
    private String contents;
    private String time;
}
