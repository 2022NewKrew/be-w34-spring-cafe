package com.kakao.cafe.model.article;

import com.kakao.cafe.model.user.UserId;
import java.time.LocalDateTime;

public class Article {

    private final int id;
    private Title title;
    private final UserId userId;
    private Contents contents;
    private final LocalDateTime createDate;
    private boolean deleted;

    public Article(int id, Title title, UserId userId, Contents contents,
            LocalDateTime createDate, boolean deleted) {
        this.id = id;
        this.title = title;
        this.userId = userId;
        this.contents = contents;
        this.createDate = createDate;
        this.deleted = deleted;
    }

    public Article(int id, Title title, UserId userId, Contents contents) {
        this(id, title, userId, contents, LocalDateTime.now(), false);
    }

    public int getId() {
        return id;
    }

    public Title getTitle() {
        return title;
    }

    public UserId getUserId() {
        return userId;
    }

    public Contents getContents() {
        return contents;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public void setContents(Contents contents) {
        this.contents = contents;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
