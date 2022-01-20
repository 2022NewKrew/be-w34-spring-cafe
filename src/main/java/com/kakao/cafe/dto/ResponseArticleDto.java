package com.kakao.cafe.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class ResponseArticleDto {
    private long id;
//    private long authorId;
    private String author;
    private String title;
    private String content;
    private long views;
    private Date createdAt;
    private List<ResponseReplyDto> reply;

    public String getCreatedAt() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(createdAt);
    }

    public int getCountOfReply(){
        return reply.size();
    }
}
