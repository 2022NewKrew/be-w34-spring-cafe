package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class CommentDto {
    private Long commentId;
    private String name;
    private Date time;
    private String contents;
}