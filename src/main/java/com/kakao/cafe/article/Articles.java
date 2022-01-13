package com.kakao.cafe.article;

public class Articles {
    private Long id;
    private String title;
    private String contents;

    public Articles( String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
