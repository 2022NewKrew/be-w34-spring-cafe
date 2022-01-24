package com.kakao.cafe.dto.reply;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDto {
    long id;
    long userId;
    long articleId;
    String nickname;
    String comments;
    boolean canEdit;
    LocalDateTime createdAt;

    public String formattedCreatedAt() {
        return createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm"));
    }

}
