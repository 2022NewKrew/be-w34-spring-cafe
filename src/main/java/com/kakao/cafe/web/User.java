package com.kakao.cafe.web;

import com.kakao.cafe.dto.SampleUserForm;

public class User {

    private String id;
    private String passWord;
    private String name;
    private String email;
    private String content;

    private User(String id, String passWord, String name, String email, String content) {
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

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getContent() {
        return content;
    }

    public void update(SampleUserForm form){
        this.name = form.getName();
        this.email = form.getEmail();
        this.content = form.getContent();
    }

    public static User add(SampleUserForm form){
        return new User(form.getId(), form.getPassWord(), form.getName(), form.getEmail(), form.getContent());
    }

}
