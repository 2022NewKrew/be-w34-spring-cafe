package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class CommentDbDto {
    private Long commentId;
    private String Name;
    private Date time;
    private String contents;
}
