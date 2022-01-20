package com.kakao.cafe.service;

import com.kakao.cafe.dto.ReplyDto;
import com.kakao.cafe.entity.Reply;
import com.kakao.cafe.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;

    @Override
    public Long register(ReplyDto replyDto) {
        Reply reply = dtoToEntity(replyDto);
        replyRepository.save(reply);
        return reply.getReplyId();
    }

    @Override
    public List<ReplyDto> getList(Long articleId) {
        List<Reply> result = replyRepository.findAllByArticleId(articleId);
        return result.stream().map(reply -> entityToDto(reply)).collect(Collectors.toList());
    }

    @Override
    public void modify(ReplyDto replyDto) {
        Reply reply = dtoToEntity(replyDto);
        replyRepository.save(reply);
    }

    @Override
    public void remove(Long replyId) {
        replyRepository.deleteById(replyId);
    }
}
