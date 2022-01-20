package com.kakao.cafe.domain.comment.dto;

import com.kakao.cafe.domain.comment.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto {

    private Long id;
    private String writerUserId;
    private String contents;
    private LocalDateTime registerDateTime;

    private boolean isWriter = false;

    @Builder
    public CommentResponseDto(Long id, String writerUserId, String contents, LocalDateTime registerDateTime) {
        this.id = id;
        this.writerUserId = writerUserId;
        this.contents = contents;
        this.registerDateTime = registerDateTime;
    }

    public void setIsWriter(String userId) {
        if (writerUserId.equals(userId)) {
            this.isWriter = true;
        }
    }

    public static CommentResponseDto from(Comment comment) {
        return builder()
                .id(comment.getId())
                .writerUserId(comment.getWriterUserId())
                .contents(comment.getContents())
                .registerDateTime(comment.getRegisterDateTime())
                .build();
    }

}
