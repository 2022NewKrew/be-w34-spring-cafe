package com.kakao.cafe.service.reply;

import com.kakao.cafe.dto.ReplyDto;
import com.kakao.cafe.mapper.ReplyMapper;
import com.kakao.cafe.repository.reply.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;
    private final ReplyMapper replyMapper;

    @Override
    public void writeReply(ReplyDto replyDto) {
        replyRepository.save(replyMapper.toReplyEntity(replyDto));
    }

    @Override
    public void deleteReply(Long replyId) {
        replyRepository.deleteById(replyId);
    }

    @Override
    public List<ReplyDto> allReplies() {
        return null;
    }

    @Override
    public List<ReplyDto> repliesByArticle(Long articleId) {
        return replyMapper.toReplyDtoList(replyRepository.findByReplyId(articleId));
    }
}
