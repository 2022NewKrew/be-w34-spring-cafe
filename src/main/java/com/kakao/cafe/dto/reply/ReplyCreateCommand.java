package com.kakao.cafe.dto.reply;

public class ReplyCreateCommand {
    private final long articleId;
    private final String writerId;
    private final String contents;

    public ReplyCreateCommand(long articleId, String writerId, String contents) {
        this.articleId = articleId;
        this.writerId = writerId;
        this.contents = contents;
    }

    public long getArticleId() {
        return articleId;
    }

    public String getWriterId() {
        return writerId;
    }

    public String getContents() {
        return contents;
    }
}
