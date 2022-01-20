package com.kakao.cafe.service;

import com.kakao.cafe.controller.dto.ReplyDto;
import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    public void create(ReplyDto replyDto) {
        Reply reply = ReplyDto.toEntity(replyDto);
        replyRepository.save(reply);
    }

    public List<ReplyDto> findByArticleId(int articleId) {
        return replyRepository.findByArticleId(articleId)
                .stream()
                .map(ReplyDto::from)
                .collect(Collectors.toList());
    }

    public void delete(int id, String writer) {
        Reply reply = replyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물"));
        matchWriter(reply, writer);
        replyRepository.delete(id);
    }

    private void matchWriter(Reply reply, String writer) {
        if (!reply.matchWriter(writer)) {
            throw new IllegalArgumentException("다른 사람의 댓글에 접근할 수 없다.");
        }
    }

}
