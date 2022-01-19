package com.kakao.cafe.dto;

import com.kakao.cafe.domain.Comment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommentDto {

    public static class CreateCommentRequest {
        private String contents;

        public CreateCommentRequest(String contents) {
            this.contents = contents;
        }

        public String getContents() {
            return contents;
        }
    }

    public static class ReadCommentResponse {
        private Integer id;
        private String writer;
        private String contents;
        private String createdAt;

        public ReadCommentResponse(Integer id, String writer, String contents, LocalDateTime createdAt) {
            this.id = id;
            this.writer = writer;
            this.contents = contents;
            this.createdAt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(createdAt);
        }

        public static ReadCommentResponse of(Comment comment) {
            return new ReadCommentResponse(comment.getId(), comment.getWriter(), comment.getContents(), comment.getCreatedAt());
        }
    }
}
