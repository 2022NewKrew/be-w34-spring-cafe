package com.kakao.cafe.service;

import com.kakao.cafe.domain.dto.ReplyWriteDto;
import com.kakao.cafe.domain.model.Reply;
import com.kakao.cafe.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    public void postReply(ReplyWriteDto replyWriteDto){
        replyRepository.postReply(replyWriteDto);
    }

    public List<Reply> findAllReplies(String articleId){
        return replyRepository.findAllReplies(articleId);
    }

    public void deleteReply(String id){
        replyRepository.deleteReply(id);
    }

    public String findUserIdOfReply(String id){
        return replyRepository.findUserIdOfReply(id);
    }
}
