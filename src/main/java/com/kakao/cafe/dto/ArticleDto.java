package com.kakao.cafe.dto;

import com.kakao.cafe.util.Pretty;
import org.springframework.lang.NonNull;

import java.time.ZoneId;
import java.util.Locale;

public class ArticleDto {
    private long idx;
    private String userId;
    private String userName;
    private String title;
    private String body;
    private String[] bodyLines;
    private String createdAtPretty;
    private String modifiedAtPretty;
    private int countComments;

    static public ArticleDto from(
            final long idx,
            @NonNull final String userId,
            @NonNull final String userName,
            @NonNull final String title,
            @NonNull final String body,
            final long createdAt,
            final long modifiedAt,
            final int countComments
    )
    {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setIdx(idx);
        articleDto.setUserId(userId);
        articleDto.setUserName(userName);
        articleDto.setTitle(title);
        articleDto.setBody(body);
        articleDto.setBodyLines(
                Pretty.splitByNewLine(body)
        );
        articleDto.setCreatedAtPretty(
                Pretty.epochSecond(createdAt, Locale.KOREA, ZoneId.of("Asia/Seoul"))
        );
        if (modifiedAt != 0L) {
            articleDto.setModifiedAtPretty(
                    Pretty.epochSecond(modifiedAt, Locale.KOREA, ZoneId.of("Asia/Seoul"))
            );
        }
        articleDto.setCountComments(countComments);
        return articleDto;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String[] getBodyLines() {
        return bodyLines;
    }

    public void setBodyLines(String[] bodyLines) {
        this.bodyLines = bodyLines;
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

    public int getCountComments() {
        return countComments;
    }

    public void setCountComments(int countComments) {
        this.countComments = countComments;
    }
}
