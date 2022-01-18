package com.kakao.cafe.domain.article;

import lombok.Getter;
<<<<<<< HEAD
import lombok.Setter;
=======
>>>>>>> 314ff13 ([feature] Article 클래스 생성)

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
<<<<<<< HEAD
@Setter
public class Article {
    private int index;
    private String writer;
    private String title;
//    private List<Content> contents;
    private String contents;
    private String time;

    public Article(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
//        this.contents = createContents(contents);
        this.contents = contents;
=======
public class Article {
    private static int index;
    private String writer;
    private String title;
    private List<Content> contents;
    private String time;

    Article(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = createContents(contents);
>>>>>>> 314ff13 ([feature] Article 클래스 생성)
        index = ArticleList.getArticleList().size() + 1;
        time = getCreatedTime();
    }

    private List<Content> createContents(String contents){
        return Arrays.stream(contents.split("\n")).map(Content::new).collect(Collectors.toList());
    }

    private String getCreatedTime() {
        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return nowDate.format(dateFormatter) + " " + nowTime.format(timeFormatter);
    }

<<<<<<< HEAD
    public String getContents(){
=======
    public List<Content> getContents(){
>>>>>>> 314ff13 ([feature] Article 클래스 생성)
        return contents;
    }
}
