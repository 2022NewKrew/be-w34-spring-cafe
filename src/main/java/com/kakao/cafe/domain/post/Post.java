package com.kakao.cafe.domain.post;

import com.kakao.cafe.model.PostModel;

public class Post {

    private final Title title;
    private final Writer writer;
    private final Contents contents;
    private long id = -1;

    public Post(PostModel postModel) {
        this.title = new Title(postModel.getTitle());
        this.writer = new Writer(postModel.getWriter());
        this.contents = new Contents(postModel.getContents());
    }

    public Post(String title, String writer, String contents, long id) {
        this.title = new Title(title);
        this.writer = new Writer(writer);
        this.contents = new Contents(contents);
        this.id = id;
    }

    private Post(Builder builder) {
        this(builder.title, builder.writer, builder.contents, builder.id);
    }

    public String getTitle() {
        return title.getTitle();
    }

    public String getWriter() {
        return writer.getWriter();
    }

    public String getContents() {
        return contents.getContents();
    }

    public long getId() {
        return this.id;
    }

    public static class Builder {
        String title;
        String writer;
        String contents;
        long id = -1;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder writer(String writer) {
            this.writer = writer;
            return this;
        }

        public Builder contents(String contents) {
            this.contents = contents;
            return this;
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Post build() {
            return new Post(this);
        }

    }

}
