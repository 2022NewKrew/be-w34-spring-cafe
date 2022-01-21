package com.kakao.cafe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Article {

    private int id;
    private String writer;
    private String title;
    private String contents;
    private int userPk;
    private boolean deleted;

    public void updateArticle(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void deleteArticle() {
        this.deleted = true;
    }

}
