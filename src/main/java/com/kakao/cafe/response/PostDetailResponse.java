package com.kakao.cafe.response;

import com.kakao.cafe.domain.Post;
import com.kakao.cafe.domain.User;
import java.util.Date;

public class PostDetailResponse {

    private final int postId;
    private final int userId;
    private final String name;
    private final String title;
    private final String content;
    private final Date createdAt;

    private PostDetailResponse(int postId, int userId, String name, String title, String content, Date createdAt) {
        this.postId = postId;
        this.userId = userId;
        this.name = name;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static PostDetailResponse of(Post post, User user) {
        return new PostDetailResponse(post.getId(), user.getId(), user.getUserName(), post.getTitle(),
                post.getContent(), post.getCreatedAt());
    }
}
