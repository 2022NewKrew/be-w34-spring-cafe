package com.kakao.cafe.domain;

import com.kakao.cafe.dto.SampleUserForm;

public class User {

    private Long id;
    private String uid;
    private String passWord;
    private String name;
    private String email;
    private String content;

    private User(String uid, String passWord, String name, String email, String content) {
        this.uid = uid;
        this.passWord = passWord;
        this.name = name;
        this.email = email;
        this.content = content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getUid() {
        return uid;
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

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public boolean isVoid(){
        if(id.equals(Long.valueOf(-1))){
            return true;
        }
        return false;
    }

    public static User add(SampleUserForm form){
        return new User(form.getId(), form.getPassWord(), form.getName(), form.getEmail(), form.getContent());
    }

    public static User voidUser(){
        User user = new User("","","","","");
        user.setId(Long.valueOf(-1));
        return user;
    }

}
