package com.kakao.cafe.model.article;

import com.kakao.cafe.model.reply.Reply;
import com.kakao.cafe.model.user.UserId;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Article {

    private final int id;
    private Title title;
    private final UserId userId;
    private Contents contents;
    private final LocalDateTime createDate;
    private boolean deleted;
    private List<Reply> replies;

    public Article(int id, Title title, UserId userId, Contents contents,
            LocalDateTime createDate, boolean deleted, List<Reply> replies) {
        this.id = id;
        this.title = title;
        this.userId = userId;
        this.contents = contents;
        this.createDate = createDate;
        this.deleted = deleted;
        this.replies = replies;
    }

    public Article(int id, Title title, UserId userId, Contents contents) {
        this(id, title, userId, contents, LocalDateTime.now(), false, new ArrayList<>());
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

    public List<Reply> getReplies() {
        return replies;
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

    public void setReplies(List<Reply> replies) {
        this.replies = new ArrayList<>(replies);
    }
}
