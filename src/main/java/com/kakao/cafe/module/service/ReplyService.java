package com.kakao.cafe.module.service;

import com.kakao.cafe.module.model.domain.Reply;
import com.kakao.cafe.module.repository.ArticleRepository;
import com.kakao.cafe.module.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kakao.cafe.module.model.dto.ReplyDtos.*;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;

    public void postReply(Long articleId, Long authorId, ReplyPostDto replyPostDto) {
        replyRepository.addReply(Reply.builder()
                .articleId(articleId)
                .authorId(authorId)
                .comment(replyPostDto.getComment())
                .build());
        articleRepository.updateArticleCommentCount(articleId, 1);
    }

    public List<ReplyReadDto> replyList(Long articleId) {
        return replyRepository.findRepliesByArticleId(articleId);
    }

    public ReplyDto getReply(Long id) {
        return modelMapper.map(replyRepository.findReplyById(id), ReplyDto.class);
    }

    public void deleteReply(Long articleId, Long replyId) {
        replyRepository.deleteReply(replyId);
        articleRepository.updateArticleCommentCount(articleId, -1);
    }
}
