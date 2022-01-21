package com.kakao.cafe.reply.service;

import com.kakao.cafe.reply.domain.Reply;
import com.kakao.cafe.reply.repository.ReplyRepository;
import com.kakao.cafe.reply.web.dto.ReplySaveDto;
import com.kakao.cafe.reply.web.dto.ReplyShowDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    public void addReply(ReplySaveDto replySaveDto) {
        Reply reply = Reply.builder()
            .writer(replySaveDto.getWriter())
            .article(replySaveDto.getArticle())
            .contents(replySaveDto.getContents())
            .build();
        replyRepository.save(reply);
    }

    public List<ReplyShowDto> findReplyByArticle(int article) {
        return replyRepository.findByArticle(article).stream()
            .map(this::createReplyShowDto)
            .collect(Collectors.toList());
    }

    private ReplyShowDto createReplyShowDto(Reply reply) {
        return ReplyShowDto.builder()
            .id(reply.getId())
            .writer(reply.getWriter())
            .contents(reply.getContents())
            .build();
    }

    public void removeReply(int id) {
        replyRepository.delete(id);
    }

    public String findReplyWriter(int id) {
        return replyRepository.findById(id).getWriter();
    }

    public int findReplyArticle(int id) {
        return replyRepository.findById(id).getArticle();
    }
}
