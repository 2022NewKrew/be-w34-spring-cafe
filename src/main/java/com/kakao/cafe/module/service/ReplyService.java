package com.kakao.cafe.module.service;

import com.kakao.cafe.module.model.domain.Reply;
import com.kakao.cafe.module.repository.ArticleRepository;
import com.kakao.cafe.module.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kakao.cafe.module.model.dto.ReplyDtos.*;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;

    public void postReply(Long articleId, Long authorId, ReplyPostDto replyPostDto) {
        replyRepository.addReply(Reply.builder()
                .articleId(articleId)
                .authorId(authorId)
                .comment(replyPostDto.getComment())
                .build());
        articleRepository.updateArticleCommentCount(articleId);
    }

    public List<ReplyReadDto> replyList(Long articleId) {
        return replyRepository.findRepliesByArticleId(articleId);
    }
}
