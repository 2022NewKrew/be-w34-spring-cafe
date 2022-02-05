package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
@AllArgsConstructor
public class ArticleSaveDto implements Serializable {
    private String writerId;
    private Date writeTime;
    private String title;
    private String contents;
}
