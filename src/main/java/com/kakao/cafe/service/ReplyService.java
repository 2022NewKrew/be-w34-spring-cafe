package com.kakao.cafe.service;

import com.kakao.cafe.dto.reply.ReplyCreateDto;

import javax.servlet.http.HttpSession;

public interface ReplyService {
    void create(ReplyCreateDto replyCreateDto, int questionId, HttpSession session);

    void delete(int answerId, HttpSession session);
}
