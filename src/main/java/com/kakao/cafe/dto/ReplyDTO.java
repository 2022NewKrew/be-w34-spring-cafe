package com.kakao.cafe.dto;

import com.kakao.cafe.persistence.model.Reply;
import java.time.format.DateTimeFormatter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface ReplyDTO {

    @Getter
    @AllArgsConstructor
    class Create {

        @NotBlank
        String body;
    }

    @Getter
    @AllArgsConstructor
    class Update {

        @NotBlank
        String body;
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    class Result {

        static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";

        Long id;
        Long articleId;
        String uid;
        String userName;
        String body;
        String createdAt;

        public static Result from(Reply reply) {
            return new Result(reply.getId(), reply.getArticleId(), reply.getUid(),
                reply.getUserName(), reply.getBody(),
                reply.getCreatedAt().format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
        }
    }
}
