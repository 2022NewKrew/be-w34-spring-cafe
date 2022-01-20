package com.kakao.cafe.qna;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by melodist
 * Date: 2022-01-19 019
 * Time: 오후 5:48
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {

    private Integer id;

    private String writer;
    private String contents;

    private Boolean isDeleted;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public BaseEntity(String writer, String contents) {
        this.id = null;

        this.writer = writer;
        this.contents = contents;

        this.isDeleted = Boolean.FALSE;

        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void updateContents(String contents) {
        this.contents = contents;
        this.modifiedDate = LocalDateTime.now();
    }

    public void deleteEntity() {
        this.isDeleted = Boolean.TRUE;
        this.modifiedDate = LocalDateTime.now();
    }
}
