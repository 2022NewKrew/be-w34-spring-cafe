package com.kakao.cafe.dto;

public class SampleUserForm {

    private String id;
    private String passWord;
    private String name;
    private String email;
    private String content;

    public SampleUserForm(String id, String passWord, String name, String email, String content) {
        this.id = id;
        this.passWord = passWord;
        this.name = name;
        this.email = email;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "SampleUserForm{" +
                "id='" + id + '\'' +
                ", passWord='" + passWord + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
