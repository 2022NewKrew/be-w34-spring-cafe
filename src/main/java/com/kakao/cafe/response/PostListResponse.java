package com.kakao.cafe.response;

import com.kakao.cafe.domain.Post;
import com.kakao.cafe.domain.User;
import java.util.Date;

public class PostListResponse {

    private final int postId;
    private final int userId;
    private final String name;
    private final String title;
    private final Date createdAt;

    private PostListResponse(int postId, int userId, String name, String title, Date createdAt) {
        this.postId = postId;
        this.userId = userId;
        this.name = name;
        this.title = title;
        this.createdAt = createdAt;
    }

    public static PostListResponse of(Post post, User user) {
        return new PostListResponse(post.getId(), user.getId(), user.getUserName(), post.getTitle(), post.getCreatedAt());
    }

    @Override
    public String toString() {
        return "PostListResponse{" +
                "postId=" + postId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
