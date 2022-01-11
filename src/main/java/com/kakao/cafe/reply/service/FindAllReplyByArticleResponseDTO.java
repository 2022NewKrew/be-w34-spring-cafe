package com.kakao.cafe.reply.service;

import com.kakao.cafe.reply.domain.Reply;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class FindAllReplyByArticleResponseDTO {
    public ArrayList<OneReplyDataResponseDTO> allReplyByArticleDTO;

    public FindAllReplyByArticleResponseDTO(ArrayList<Reply> list){
        this.allReplyByArticleDTO = list.stream().map(reply->new OneReplyDataResponseDTO(reply.getAuthorId(), reply.getWriteTime(), reply.getContents())).collect(Collectors.toCollection(ArrayList::new));
    }

    @AllArgsConstructor
    public static class OneReplyDataResponseDTO {
        public Long userId;
        public LocalDateTime writeTime;
        public String contents;
    }

}
