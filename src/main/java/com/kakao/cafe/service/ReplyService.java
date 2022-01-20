package com.kakao.cafe.service;

import com.kakao.cafe.controller.dto.ReplyDto;
import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void delete(int id) {
        replyRepository.delete(id);
    }


}
