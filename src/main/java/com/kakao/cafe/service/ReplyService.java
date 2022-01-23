package com.kakao.cafe.service;

import com.kakao.cafe.domain.Post;
import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.repository.JdbcTemplatePostRepository;
import com.kakao.cafe.repository.JdbcTemplateReplyRepository;
import com.kakao.cafe.repository.PostRepository;
import com.kakao.cafe.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    @Autowired
    public ReplyService(JdbcTemplateReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    public void create(Reply reply){
        replyRepository.save(reply);
    }

    public List<Reply> findAll(int postid){
        return replyRepository.findAll(postid);
    }

    public Optional<Reply> findById(int Id){
        return replyRepository.findById(Id);
    }

    public void delete(Reply reply){
        replyRepository.delete(reply);
    }
}
