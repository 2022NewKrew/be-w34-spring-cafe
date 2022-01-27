package com.kakao.cafe.qna.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Getter
public class Article {
    private final int id;
    private final String writer;

    @Setter
    private String title;

    @Setter
    private String content;

    private final String writtenTime;

    @Getter(AccessLevel.NONE)
    private int point;

    @Getter(AccessLevel.NONE)
    private final ReadWriteLock pointLock;
    @Getter(AccessLevel.NONE)
    private final Lock pointReadLock;
    @Getter(AccessLevel.NONE)
    private final Lock pointWriteLock;

    public Article(int id, String writer, String title, String content) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;

        this.writtenTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
        this.point = 0;
        this.pointLock = new ReentrantReadWriteLock();
        this.pointReadLock = pointLock.readLock();
        this.pointWriteLock = pointLock.writeLock();
    }

    public void increasePoint() {
        pointWriteLock.lock();
        this.point++;
        pointWriteLock.unlock();
    }

    public void decreasePoint() {
        pointWriteLock.lock();
        this.point--;
        pointWriteLock.unlock();
    }

    public int getPoint() {
        pointReadLock.lock();
        int returnPoint = this.point;
        pointReadLock.unlock();
        return returnPoint;
    }
}
