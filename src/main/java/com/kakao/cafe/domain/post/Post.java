package com.kakao.cafe.domain.post;

public class Post {

    private final Title title;
    private final Writer writer;
    private final Contents contents;
    private long id = -1;

    public Post(String title, String writer, String contents) {
        this.title = new Title(title);
        this.writer = new Writer(writer);
        this.contents = new Contents(contents);
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

    public boolean isWriter(String curId) {
        return writer.is(curId);
    }

    public static class Builder {
        private String title;
        private String writer;
        private String contents;
        private long id = -1;

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
