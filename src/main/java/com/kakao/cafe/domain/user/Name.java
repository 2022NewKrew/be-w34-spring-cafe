package com.kakao.cafe.domain.user;

public class Name {

    private final String name;

    public Name(String name){
        if(name == null)
            throw new IllegalArgumentException("잘못된 입력입니다!");
        name = name.trim();
        if(name.length() == 0)
            throw new IllegalArgumentException("잘못된 입력입니다!");
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
