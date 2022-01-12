package com.kakao.cafe.testutil.post;

import com.kakao.cafe.dto.post.PostViewDto;

public final class PostDtoUtil {

    private PostDtoUtil() {
    }

    public static PostViewDto createPostViewDto() {
        return createPostViewDto(Long.valueOf(1));
    }

    public static PostViewDto createPostViewDto(Long postId) {
        return PostViewDto.builder()
                .id(postId)
                .title("title")
                .writerNickName("gallix")
                .createdDateTime("2022-01-01 00:00:00")
                .viewNum(100)
                .build();
    }
}
