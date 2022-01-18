package com.kakao.cafe.article.dto;

public class ArticleUpdateDTO {
    private Long sequence;
    private String userId;
    private String name; //User의 name과 동일
    private String title;
    private String contents;

    public ArticleUpdateDTO(Long sequence, String userId, String title, String contents) {
        this.sequence = sequence;
        this.userId = userId;
        this.title = title;
        this.contents = contents;
    }

    public Long getSequence() {
        return sequence;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
