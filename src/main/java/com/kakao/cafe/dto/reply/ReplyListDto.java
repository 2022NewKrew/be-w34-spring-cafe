package com.kakao.cafe.dto.reply;

import com.kakao.cafe.domain.Reply;

import java.util.List;
import java.util.stream.Collectors;

public class ReplyListDto {
    private int total;
    private List<ReplyListItemDto> replyList;

    public ReplyListDto(int total, List<ReplyListItemDto> replyList) {
        this.total = total;
        this.replyList = replyList;
    }

    public static ReplyListDto of(List<Reply> replyList) {
        return new ReplyListDto(
                replyList.size(),
                replyList.stream()
                .map(ReplyListItemDto::of)
                .collect(Collectors.toList()));
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ReplyListItemDto> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<ReplyListItemDto> replyList) {
        this.replyList = replyList;
    }
}
