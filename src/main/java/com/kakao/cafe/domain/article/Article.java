package com.kakao.cafe.domain.article;

public class Article {
    private int id;
    private String title;
    private String content;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof Article))
            return false;

        Article article = (Article) obj;
        return article.id == id &&
                article.title.equals(title) &&
                article.content.equals(content);
    }
}
