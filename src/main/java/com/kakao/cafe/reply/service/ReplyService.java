package com.kakao.cafe.reply.service;

import com.kakao.cafe.reply.dto.ReplyResDto;
import com.kakao.cafe.reply.entity.ReplyEntity;
import com.kakao.cafe.reply.mapper.ReplyMapper;
import com.kakao.cafe.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final ReplyMapper replyMapper;

    public List<ReplyResDto> getReplyListByArticleId(Long articleId) {
        List<ReplyEntity> replyEntityList = replyRepository.findAllByArticleId(articleId);
        return replyMapper.toReplyResDtoList(replyEntityList);
    }

    public void saveReply(Long articleId, String writer, String comment) {
        ReplyEntity replyEntity = ReplyEntity.builder()
                .articleId(articleId)
                .writer(writer)
                .comment(comment)
                .build();

        replyRepository.save(replyEntity);
    }

    public void delete(Long articleId, Long replyId) {
        replyRepository.delete(articleId, replyId);
    }
}
