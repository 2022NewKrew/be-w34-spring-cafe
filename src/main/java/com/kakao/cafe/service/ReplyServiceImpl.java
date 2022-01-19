package com.kakao.cafe.service;

import com.kakao.cafe.domain.Member;
import com.kakao.cafe.domain.Post;
import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.dto.member.MemberDto;
import com.kakao.cafe.dto.reply.ReplyCreateDto;
import com.kakao.cafe.repository.PostRepository;
import com.kakao.cafe.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;

    @Autowired
    public ReplyServiceImpl(ReplyRepository replyRepository,
                            PostRepository postRepository) {
        this.replyRepository = replyRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void create(ReplyCreateDto replyCreateDto, int questionId, HttpSession session) {
        Member member = (Member)session.getAttribute("sessionedUser");

        Post post = postRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("post not found"));

        Reply reply = Reply.of(replyCreateDto, post, member);
        replyRepository.save(reply);
    }
}
