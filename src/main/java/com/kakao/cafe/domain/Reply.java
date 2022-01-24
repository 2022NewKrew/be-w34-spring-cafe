package com.kakao.cafe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Reply {
    private int id;             // PK
    private int aid;            // article - id
    private String writer;      // member - userId
    private String contents;
}
