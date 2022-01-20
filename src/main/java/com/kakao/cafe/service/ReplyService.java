package com.kakao.cafe.service;

import com.kakao.cafe.dto.ReplyDto;
import com.kakao.cafe.dto.ReplyRequestDto;
import com.kakao.cafe.entity.Reply;
import com.kakao.cafe.entity.User;
import com.kakao.cafe.repository.ReplyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReplyService {
    private final ReplyRepository replyRepository;

    public ReplyService(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    public void createRelpy(String index, ReplyRequestDto replyRequestDto, User sessionedUser) {
        Reply reply = new Reply(replyRequestDto, sessionedUser, Integer.parseInt(index));
        replyRepository.save(reply);
    }

    public List<ReplyDto> getReplyList(String articleId) {
        return replyRepository.findByArticleId(articleId)
                .stream()
                .map(ReplyDto::entityToDto)
                .collect(Collectors.toList());
    }
}