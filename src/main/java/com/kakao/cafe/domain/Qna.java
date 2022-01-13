package com.kakao.cafe.domain;

import com.kakao.cafe.utils.DateUtils;

public class Qna {
    private int views;
    private long qnaId;
    private String title;
    private String content;
    private String createDate;

    private Qna() { }

    private Qna(Qna qna) {
        this.views = qna.views;
        this.qnaId = qna.qnaId;
        this.title = qna.title;
        this.content = qna.content;
        this.createDate = DateUtils.getCurrentTime();
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public static Qna from(Qna qna) {
        return new Qna(qna);
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

    public String getCreateDate() {
        return createDate;
    }

    @Override
    public String toString() {
        return "Qna{" +
                "views=" + views +
                ", qnaId=" + qnaId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
