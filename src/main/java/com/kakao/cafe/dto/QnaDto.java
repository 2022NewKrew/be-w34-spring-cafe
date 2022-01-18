package com.kakao.cafe.dto;

import com.kakao.cafe.domain.Qna;

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
        private Integer index;
        private String writer;
        private String title;
        private String contents;

        public QnaResponse(Integer index, String writer, String title, String contents) {
            this.index = index;
            this.writer = writer;
            this.title = title;
            this.contents = contents;
        }

        public static QnaResponse of(Qna qna) {
            return new QnaResponse(qna.getIndex(), qna.getWriter(), qna.getTitle(), qna.getContents());
        }
    }

    public static class QnaForUpdateReponse {
        private Integer index;
        private String writer;
        private String title;
        private String contents;

        public QnaForUpdateReponse(Integer index, String writer, String title, String contents) {
            this.index = index;
            this.writer = writer;
            this.title = title;
            this.contents = contents;
        }

        public static QnaForUpdateReponse of(Qna qna) {
            return new QnaForUpdateReponse(qna.getIndex(), qna.getWriter(), qna.getTitle(), qna.getContents());
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

        public String getWriter() {
            return writer;
        }

        public String getContents() {
            return contents;
        }

        public void setWriter(String writer) {
            this.writer = writer;
        }

        public Qna toEntity(Integer index) {
            return new Qna(index, writer, title, contents);
        }
    }
}
