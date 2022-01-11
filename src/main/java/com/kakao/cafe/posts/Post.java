package com.kakao.cafe.posts;

public class Post {
    private String title;
    private String writer;
    private int year;
    private int month;
    private int day;
    private int count;
    private int ID;

    public Post(String title, String writer, int year, int month, int day, int count, int ID) {
        this.title = title;
        this.writer = writer;
        this.year = year;
        this.month = month;
        this.day = day;
        this.count = count;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getCount() {
        return count;
    }
}
