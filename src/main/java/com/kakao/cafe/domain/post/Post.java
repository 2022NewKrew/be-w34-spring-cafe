package com.kakao.cafe.domain.post;

public class Post {
    private Long id;
    private String writer;
    private String title;
    private String body;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
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

    public static final class PostBuilder {
        private Long id;
        private String writer;
        private String title;
        private String body;

        private PostBuilder() {
        }

        public static PostBuilder aPost() {
            return new PostBuilder();
        }

        public PostBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public PostBuilder withWriter(String writer) {
            this.writer = writer;
            return this;
        }

        public PostBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public PostBuilder withBody(String body) {
            this.body = body;
            return this;
        }

        public Post build() {
            Post post = new Post();
            post.setId(id);
            post.setWriter(writer);
            post.setTitle(title);
            post.setBody(body);
            return post;
        }
    }
}
