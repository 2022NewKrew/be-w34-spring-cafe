package com.kakao.cafe.testutil.post;

import com.kakao.cafe.dto.post.AddPostDto;
import com.kakao.cafe.dto.post.PostViewDto;

public final class PostDtoUtil {

    private PostDtoUtil() {
    }

    public static AddPostDto createAddPostDto() {
        return createAddPostDto("title", "contents for gallix is good thing");
    }

    public static AddPostDto createAddPostDto(String title, String contents) {
        return AddPostDto.builder()
                .title(title)
                .contents(contents)
                .build();
    }

    public static PostViewDto createPostViewDto() {
        return createPostViewDto(Long.valueOf(1));
    }

    public static PostViewDto createPostViewDto(Long postId) {
        return PostViewDto.builder()
                .id(postId)
                .title("title")
                .contents("contents")
                .writerNickName("gallix")
                .createdDateTime("2022-01-01 00:00:00")
                .viewNum(100)
                .build();
    }
}
