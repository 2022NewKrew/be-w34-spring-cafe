package com.kakao.cafe.service;

import com.kakao.cafe.domain.dto.ReplyWriteDto;
import com.kakao.cafe.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    public void postReply(ReplyWriteDto replyWriteDto){
        replyRepository.postReply(replyWriteDto);
    }
}
