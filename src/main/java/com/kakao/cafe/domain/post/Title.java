package com.kakao.cafe.domain.post;

public class Title {
    private String title;
    public Title(String title){
        if(title == null)
            throw new IllegalArgumentException("잘못된 입력입니다!");
        title = title.trim();
        if(title.length() == 0)
            throw  new IllegalArgumentException("잘못된 입력입니다!");
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
