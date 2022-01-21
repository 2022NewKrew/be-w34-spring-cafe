package com.kakao.cafe.application;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.post.PostRepository;
import com.kakao.cafe.domain.post.reply.Reply;
import com.kakao.cafe.domain.post.reply.ReplyMapper;
import com.kakao.cafe.domain.post.reply.ReplyRepository;
import com.kakao.cafe.interfaces.common.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;
    private final ReplyMapper replyMapper;

    public void write(ReplyDto replyDto, long postId) {
        Post post = postRepository.getById(postId);
        Reply reply = replyMapper.toEntity(replyDto);
        post.addReply(reply);
        replyRepository.save(reply);
    }

    public List<Reply> findByPostId(long postId) {
        return replyRepository.findByPost_Id(postId);
    }
}
