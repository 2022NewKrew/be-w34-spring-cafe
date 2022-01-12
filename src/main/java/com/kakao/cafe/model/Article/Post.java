package com.kakao.cafe.model.Article;

public class Post {
    private Long id;
    private final String title;
    private final String content;

    public Post(PostCreateRequestDto post) {
        this.title = post.getTitle();
        this.content = post.getContent();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PostResponseDto toResponseDto() {
        return new PostResponseDto(title, content);
    }
}
