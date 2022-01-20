package com.kakao.cafe.dto.article;

import com.kakao.cafe.domain.entity.Article;
import com.kakao.cafe.repository.UserRepository;

public class ArticleWithWriterName {
    private final long articleId;
    private final String time;
    private final String writer;
    private final String writerId;
    private final String title;
    private final String content;

    public ArticleWithWriterName(long articleId, String writer, String writerId, String title, String contents, String time) {
        this.articleId = articleId;
        this.writer = writer;
        this.writerId = writerId;
        this.title = title;
        this.content = contents;
        this.time = time;
    }

    public ArticleWithWriterName(Article article, UserRepository userRepository) {
        this.articleId = article.getArticleId();
        this.writer = userRepository.search(article.getWriterId()).getName();
        this.writerId = article.getWriterId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.time = article.getTime();
    }

    public long getArticleId() { return articleId; }

    public String getTitle() { return title; }

    public String getWriter() { return writer; }

    public String getWriterId() { return writerId; }

    public String getTime() { return time; }

    public String getContent() { return content; }
}
