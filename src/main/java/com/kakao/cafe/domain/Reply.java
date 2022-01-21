package com.kakao.cafe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reply {

    private int id;
    private int articleId;
    private String writer;
    private String contents;
    private int userPk;
    private boolean deleted;

    public void deleteReply() {
        this.deleted = true;
    }

}
