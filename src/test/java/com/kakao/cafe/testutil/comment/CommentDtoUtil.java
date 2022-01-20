package com.kakao.cafe.testutil.comment;

import com.kakao.cafe.dto.comment.SaveCommentDto;

public final class CommentDtoUtil {

    private CommentDtoUtil() {
    }

    public static SaveCommentDto createdSaveCommentDto() {
        return createdSaveCommentDto("good post!", Long.valueOf(1));
    }

    public static SaveCommentDto createdSaveCommentDto(Long postId) {
        return createdSaveCommentDto("good post!", postId);
    }

    public static SaveCommentDto createdSaveCommentDto(String contents, Long postId) {
        return SaveCommentDto.builder()
                .contents(contents)
                .postId(postId)
                .build();
    }
}
