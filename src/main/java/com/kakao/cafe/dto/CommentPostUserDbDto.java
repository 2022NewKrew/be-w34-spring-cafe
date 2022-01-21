package com.kakao.cafe.dto;

public class CommentPostUserDbDto {
    // comment
    private long id;
    private String text;
    // post
    private long postId;
    private String title;
    private String contents;
    private String userId;
    // user
    private String password;
    private String email;
    private String name;

    public CommentPostUserDbDto(long id, String text, long postId, String title, String contents, String userId, String password, String email, String name) {
        this.id = id;
        this.text = text;
        this.postId = postId;
        this.title = title;
        this.contents = contents;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.name = name;
    }


    public CommentPostUserDbDto(Builder builder) {
        this(
                builder.getId(),
                builder.getText(),
                builder.getPostId(),
                builder.getTitle(),
                builder.getContents(),
                builder.getUserId(),
                builder.getPassword(),
                builder.getEmail(),
                builder.getName()
        );
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Builder {
        // comment
        private long id = -1;
        private String text;
        // post
        private long postId;
        private String title;
        private String contents;
        // user
        private String userId;
        private String password;
        private String email;
        private String name;


        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder postId(long postId) {
            this.postId = postId;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder contents(String contents) {
            this.contents = contents;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public long getId() {
            return id;
        }

        public String getText() {
            return text;
        }


        public long getPostId() {
            return postId;
        }

        public String getTitle() {
            return title;
        }

        public String getContents() {
            return contents;
        }

        public String getUserId() {
            return userId;
        }

        public String getPassword() {
            return password;
        }

        public String getEmail() {
            return email;
        }

        public String getName() {
            return name;
        }

        public CommentPostUserDbDto build() {
            return new CommentPostUserDbDto(this);
        }
    }


}
