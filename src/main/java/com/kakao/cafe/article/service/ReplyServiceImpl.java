package com.kakao.cafe.article.service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.kakao.cafe.article.dto.request.ReplyCreateRequestDTO;
import com.kakao.cafe.article.dto.response.ReplyFindResponseDTO;
import com.kakao.cafe.article.entity.Reply;
import com.kakao.cafe.article.repository.ReplyRepository;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;

    @Override
    public void create(ReplyCreateRequestDTO replyCreateRequestDTO) {
        replyRepository.save(replyCreateRequestDTO.toEntity());
    }

    @Override
    public ReplyFindResponseDTO getReplyById(int id) {
        Reply reply = replyRepository.findById(id).orElseThrow();

        return new ReplyFindResponseDTO(reply);
    }

    @Override
    public List<ReplyFindResponseDTO> getAllReplyByArticleId(int articleId) {
        return replyRepository.findAllByArticleId(articleId).stream()
                              .map(ReplyFindResponseDTO::new)
                              .collect(Collectors.toList());
    }

    @Override
    public void remove(int id) {
        replyRepository.delete(id);
    }
}
