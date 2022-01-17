package com.kakao.cafe.domain;

import java.time.LocalDateTime;

public class Qna {
    private int views;
    private long qnaId;
    private String title;
    private String content;
    private LocalDateTime createDate;

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public long getQnaId() {
        return qnaId;
    }

    public void setQnaId(long qnaId) {
        this.qnaId = qnaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Qna{" +
                "views=" + views +
                ", qnaId=" + qnaId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
