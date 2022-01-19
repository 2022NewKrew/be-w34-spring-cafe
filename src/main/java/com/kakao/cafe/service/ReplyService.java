package com.kakao.cafe.service;

import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class ReplyService {
    private final ReplyRepository replyRepository;

    @Autowired
    public ReplyService(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    public List<Reply> findById(String id) {
        return replyRepository.findById(id);
    }

    public void addReply(Reply reply) {
        replyRepository.addReply(reply);
    }

    public void deleteById(String id) {
        replyRepository.deleteById(id);
    }

    public void isWriter(String writer, HttpSession httpSession) {
        User curUser = (User) httpSession.getAttribute("curUser");
        String curUserId = curUser.getUserId();
        if (!writer.equals(curUserId)) {
            throw new IllegalArgumentException("다른 사람의 글을 수정, 삭제할 수 없습니다.");
        }
    }
}
