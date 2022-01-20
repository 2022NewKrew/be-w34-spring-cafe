package com.kakao.cafe.dto;

import com.kakao.cafe.domain.Qna;

import java.util.List;

public class QnaDto {

    public static class CreateQnaRequest {
        private String writer;
        private String title;
        private String contents;

        public CreateQnaRequest(String writer, String title, String contents) {
            this.writer = writer;
            this.title = title;
            this.contents = contents;
        }

        public Qna toQnaEntity() {
            return new Qna(writer, title, contents);
        }

        public String getWriter() {
            return writer;
        }

        public String getTitle() {
            return title;
        }

        public String getContents() {
            return contents;
        }

        public void setWriter(String writer) {
            this.writer = writer;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setContents(String contents) {
            this.contents = contents;
        }
    }

    public static class QnaResponse {
        private Integer qnaId;
        private String writer;
        private String title;
        private String contents;
        private List<CommentDto.ReadCommentResponse> comments;

        public QnaResponse(Integer qnaId, String writer, String title, String contents) {
            this.qnaId = qnaId;
            this.writer = writer;
            this.title = title;
            this.contents = contents;
        }

        public QnaResponse(Integer qnaId, String writer, String title, String contents, List<CommentDto.ReadCommentResponse> comments) {
            this.qnaId = qnaId;
            this.writer = writer;
            this.title = title;
            this.contents = contents;
            this.comments = comments;
        }

        public static QnaResponse of(Qna qna) {
            return new QnaResponse(qna.getId(), qna.getWriter(), qna.getTitle(), qna.getContents());
        }

        public static QnaResponse of(Qna qna, List<CommentDto.ReadCommentResponse> comments) {
            return new QnaResponse(qna.getId(), qna.getWriter(), qna.getTitle(), qna.getContents(), comments);
        }
    }

    public static class QnaForUpdateResponse {
        private Integer qnaId;
        private String writer;
        private String title;
        private String contents;

        public QnaForUpdateResponse(Integer qnaId, String writer, String title, String contents) {
            this.qnaId = qnaId;
            this.writer = writer;
            this.title = title;
            this.contents = contents;
        }

        public static QnaForUpdateResponse of(Qna qna) {
            return new QnaForUpdateResponse(qna.getId(), qna.getWriter(), qna.getTitle(), qna.getContents());
        }

        public String getWriter() {
            return writer;
        }
    }

    public static class UpdateQnaRequest {
        private String title;
        private String writer;
        private String contents;

        public UpdateQnaRequest(String title, String contents) {
            this.title = title;
            this.contents = contents;
        }

        public String getTitle() {
            return title;
        }

        public String getContents() {
            return contents;
        }
    }
}
