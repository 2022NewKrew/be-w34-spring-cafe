package com.kakao.cafe.service;

import com.kakao.cafe.dto.ReplyDto;
import com.kakao.cafe.dto.ReplyRequestDto;
import com.kakao.cafe.entity.Reply;
import com.kakao.cafe.entity.User;
import com.kakao.cafe.repository.ReplyRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
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

    public boolean isDeletable(String replyId, HttpSession httpSession) {
        User sessionedUser = (User) httpSession.getAttribute("sessionedUser");
        if(sessionedUser == null) return false;
        Reply reply = replyRepository.findById(replyId);
        return sessionedUser.getId() == reply.getUser().getId();
    }

    public void deleteById(String index) {
        replyRepository.deleteById(index);
    }
}