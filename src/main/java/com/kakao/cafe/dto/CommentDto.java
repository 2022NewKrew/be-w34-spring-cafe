package com.kakao.cafe.dto;

import com.kakao.cafe.util.Pretty;
import org.springframework.lang.NonNull;

import java.time.ZoneId;
import java.util.Locale;

public class CommentDto {
    private long idx;
    private String userId;
    private boolean isOwner;
    private String userName;
    private long articleIdx;
    private String body;
    private String createdAtPretty;
    private String modifiedAtPretty;

    static public CommentDto from(
            @NonNull final long idx,
            @NonNull final String userId,
            @NonNull final String userName,
            @NonNull long articleIdx,
            @NonNull final String body,
            @NonNull final long createdAt,
            @NonNull final long modifiedAt
    )
    {
        CommentDto commentDto = new CommentDto();
        commentDto.setIdx(idx);
        commentDto.setUserId(userId);
        commentDto.setUserName(userName);
        commentDto.setArticleIdx(articleIdx);
        commentDto.setBody(body);
        commentDto.setCreatedAtPretty(
                Pretty.epochSecond(createdAt, Locale.KOREA, ZoneId.of("Asia/Seoul"))
        );
        if (modifiedAt != 0L) {
            commentDto.setModifiedAtPretty(
                    Pretty.epochSecond(modifiedAt, Locale.KOREA, ZoneId.of("Asia/Seoul"))
            );
        }
        return commentDto;
    }

    public long getIdx() {
        return idx;
    }

    public void setIdx(long idx) {
        this.idx = idx;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getArticleIdx() {
        return articleIdx;
    }

    public void setArticleIdx(long articleIdx) {
        this.articleIdx = articleIdx;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreatedAtPretty() {
        return createdAtPretty;
    }

    public void setCreatedAtPretty(String createAtPretty) {
        this.createdAtPretty = createAtPretty;
    }

    public String getModifiedAtPretty() {
        return modifiedAtPretty;
    }

    public void setModifiedAtPretty(String modifiedAtPretty) {
        this.modifiedAtPretty = modifiedAtPretty;
    }
}
