package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ReplyDto {
    private int id;
    private int aid;            // article - id
    private String writer;      // member - userId
    private String contents;
}
