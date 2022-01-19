package com.kakao.cafe.reply.entity;

import com.kakao.cafe.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter @Setter
public class Reply {

    private Long id;
    private User writer;
    private String contents;
    private LocalDateTime createdAt;
}
