package com.kakao.cafe.reply.service;

import com.kakao.cafe.exception.UpdateException;
import com.kakao.cafe.reply.dto.ReplyResDto;
import com.kakao.cafe.reply.entity.ReplyEntity;
import com.kakao.cafe.reply.mapper.ReplyMapper;
import com.kakao.cafe.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final ReplyMapper replyMapper;

    @Transactional(readOnly = true)
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

    public void delete(Long articleId, Long replyId, String userName) {
        checkUpdatable(replyId, userName);
        replyRepository.delete(articleId, replyId);
    }

    private void checkUpdatable(Long replyId, String userName) {
        List<ReplyEntity> sameWriter = replyRepository.findByIdAndWriter(replyId, userName);
        if (sameWriter.isEmpty()) {
            throw new UpdateException("댓글 작성자만 댓글을 수정/삭제 할 수 있습니다.");
        }
    }
}
