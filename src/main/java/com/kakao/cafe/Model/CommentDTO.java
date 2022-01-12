package com.kakao.cafe.Model;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class CommentDTO {
    private String autor;
    private String content;
    private Integer commentIdx;
    private String createdDateTime;

    public CommentDTO(String autor, String content) {
        this.autor = autor;
        this.content = content;
        this.createdDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
