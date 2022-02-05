package com.kakao.cafe.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
@AllArgsConstructor
public class CommentInsertDto implements Serializable {
    private Long articleId;
    private String userId;
    private Date writeTime;
    private String contents;
}
