package com.kakao.cafe.dto;

public class SampleLoginForm {

    private String id;
    private String passWord;

    public SampleLoginForm(String id, String passWord) {
        this.id = id;
        this.passWord = passWord;
    }

    public String getId() {
        return id;
    }

    public String getPassWord() {
        return passWord;
    }

    @Override
    public String toString() {
        return "SampleLoginForm{" +
                "id='" + id + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
