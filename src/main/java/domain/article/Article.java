package domain.article;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
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

    public String getCreateUserId() {
        return createUserId;
    }
    public String getCreateDate() {
        return createDate;
    }

}
