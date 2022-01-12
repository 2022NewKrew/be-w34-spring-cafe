package domain.article;

import java.time.LocalDate;

public class Article {
    private final String title;
    private final String content;
    private final String createUserId;
    private final String createDate;
    private final int views;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
        this.createUserId = "unknown";
        this.createDate = LocalDate.now().toString();
        this.views = 0;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public int getViews() {
        return views;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createUserId='" + createUserId + '\'' +
                ", createDate='" + createDate + '\'' +
                ", views=" + views +
                '}';
    }
}
